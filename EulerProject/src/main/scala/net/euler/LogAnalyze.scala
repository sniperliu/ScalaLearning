import java.util.regex._
import scalax.data.Implicits._
import scalax.io.Implicits._
import scalax.control.ManagedSequence

/**
 * I've made this immutable, representing some fixed accumulation of times for some
 * fixed number of instances. Having this class immutable will make our lives a lot
 * easier if we ever decide to make this program concurrent. Of course, this is
 * probably IO-bound, so concurrency probably won't help unless we're reading from
 * multiple log files at the same time.

 * I've also added a + method to the class, so I can add two Times together and get
 * a new Time that represents the accumulation of both of the previous time. Yes,
 * this means I'll be creating and gargabe collecting lots of objects, but the JVM
 * GC is actually really good at disposing of short-lived objects, so the performance
 * penalty is near-zero.
 */
case class Time(instances: Int, totalTime: Int, viewTime: Int, dbTime: Int) {
    def this() = this(0, 0, 0, 0)

    def +(other: Time) =
        Time(instances + other.instances, totalTime + other.totalTime, viewTime + other.viewTime, dbTime + other.dbTime)

    def avg(time: Int) = time.toFloat/instances

    override def toString = "Total Time: " + totalTime + "ms (View: " + viewTime +"ms DB:" + dbTime + "ms )"
    def avgToString =  "Average Time: " + avg(totalTime) + "ms (View: " + avg(viewTime) +"ms DB:" + avg(dbTime) + "ms )"
}

object LogParser {
    def main(args: Array[String]) {
        val results = parseLogFile(args(0))

        println("Total URIs: " + results.size)

        for ((uri, time) <- results) {
            println(uri + " => " + time.instances)
            println("\t" + time)
            println("\t" + time.avgToString)
        }
    }

    def parseLogFile(filename: String): Map[String, Time] = {
        // Pattern is this: "Completed in 100ms (View: 25, DB: 75) | 200 OK [http://app.domain.com?params=here]"
        val p = Pattern.compile("Completed in (\\d+)ms \\(View: (\\d+), DB: (\\d+)\\) \\| (\\d+) OK \\[http://app.domain.com(.*)\\?")

        /**
         * The next two lines have some deep Scalax magic. Well, not really, but it might
         * look like deep magic if you've never used Scalax. The rest of this expression
         * is fairly straight-forward.
         *
         * First, "toFile" is a method that Scalax adds to any String to turn it into
         * a java.io.File. Finally, "lines" is a method that Scalax adds to any
         * java.io.File to turn it into a ManagedSequence[String]. ManagedSequence does
         * some Automatic Resource Management for us. It will take care of opening and
         * closing the file properly (even if an exception is thrown while processing it!)
         * and making sure that only one line is processed at a time (that is, the entire
         * file is processed in O(1) space).
         * 
         * In order to make this work, ManagedSequence is lazy. That is, defining my "times"
         * variable doesn't actually read anything from, or even open, the file. All of the
         * IO happens only once I do something with the stuff inside the ManagedSequence.
         * In this case, that happens when I call foldLeft on my ManagedSequence later on.
         */
        val times: ManagedSequence[(String, Time)] = for {
            line <- filename.toFile.lines
            val m = p.matcher(line)
                // This line filters only those lines which match our pattern.
                // Non-matching lines get skipped.
                if m.find
            val uri = m.group(5)
            val time = Time(1, m.group(1).toInt, m.group(2).toInt, m.group(3).toInt)
        } yield (uri, time)

        /**
         * This is just a standard empty Map. I've made it immutable instead of mutable,
         * in part because I prefer immutable data structures, but also because we might
         * want to add concurrency later and if we do immutability will turn out to be a
         * big bonus. I'm adding a default value to my map. That is, if I try to access
         * a key that is not in the map, I'll get an empty (zeroed out) Time instead
         * of an error.
         */
        val emptyMap: Map[String, Time] =
            Map.empty.withDefaultValue(new Time)

        /**
         * If you're not familiar with foldLeft, this can look a little scary at first.
         * Don't worry, folds are easy. Here is your most basic fold, which adds up all
         * the numbers in a List:
         *
         *   List(1, 2, 3).foldLeft(0)(_ + _) === (((0 + 1) + 2) + 3) === 6
         * 
         * The second argument to foldLeft is a function of two arguments. If we wanted
         * to name the arguments, we could expand the above code to:
         *
         *   List(1, 2, 3).foldLeft(0)((sum, n) => sum + n)
         *
         * As you can see, the second argument to foldLeft takes a running sum, which starts
         * off at zero, and the next number (n = 1, 2, or 3) and returns the new running sum
         *
         * We're going to take the same approach, but instead of keeping a running sum we're
         * going to keep a running map of Uri -> Time. We start off with the emptyMap,
         * then we provide a function which takes a running map and a pair of Uri -> Time
         * (from our ManagedSequence) and returns a new running map.
         *
         * So how do we return the new running map? Our running map is immutable, so how come
         * it looks like we're changing it? Well, let's look a closer look at the line that
         * returns our new running map.
         *
         *   map(uri) += time
         *
         * First, map(uri) would typically return a Time. However, Time has no += method defined
         * on it. Instead, Scala transforms += into a sequence of = and +, like so:
         *
         *   map(uri) = map(uri) + time
         *
         * Next, Scala turns expressions of the form a(b) = x into method calls of the form
         * a.update(b, x). It's another piece of syntactic convenience, just like a(b) is turned
         * into a.apply(b).
         *
         *   map.update(uri, map.apply(uri) + time)
         * 
         * Now this starts to make sense. Map's apply method returns the Time for that uri, if no Time
         * is found we get our default value, the empty Time. Then Time + Time returns the sum
         * of both the older Times. Finally, map's update method returns a new map with a modified
         * value for the uri key.
         *
         * (To be fair, that last line was a little too sugary even for me. I probably would have
         * written it as map(uri) = map(uri) + time. You still have to know how that translates
         * into apply and update, but that's a little more common and less prone to bugs like += is.)
         *
         * Since we're keeping a running map, every (uri, time) pair in our ManagedSequence will
         * get added to the running map we're keeping. The eventual return result will be an
         * immutable Map[String, Time]. Neat!
         *
         * Note that this is where the laziness of ManagedSequence breaks down and it's forced to
         * do all the work it's been putting off. As soon as our foldLeft starts, the file gets
         * opened and each line gets regex matched and turned into a pair of (uri, time) and then
         * incorporated into a running map of Uris -> Times. All of this is done one line at
         * a time, to make sure we don't blow up our memory if the file is really big. Once the
         * foldLeft is over, ManagedSequence takes care to close the file properly.
         *
         * Because everything is immutable, we'll be creating a lot of intermediate data structures
         * like small Times and partial Maps. However, they're so short lived (all thanks to the
         * laziness of ManagedSequence!) that they'll hopefully never leave the GC's nursery and
         * get collected fairly efficiently. If performance is a concern I'd profile the program,
         * but I would be very surprised if didn't run almost as fast as the optimal imperative version.
         */
        times.foldLeft(emptyMap) { case (map, (uri, time)) =>
            map(uri) += time
        }
    }
}
