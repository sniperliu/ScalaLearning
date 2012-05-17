package net.euler

object Test {
  def main(args: Array[String]) = {
    val xml = 
      <scheduleitem>
        <schedule>
          <frequency><daily dailyInterval="1" repeatInterval="-1" repeatDuration="86400" /></frequency>
          <startDate>2004-04-29</startDate>
          <time>17:00:00</time>
          <timezoneId>0</timezoneId>
        </schedule>
      </scheduleitem>
      
    // println((xml \\ "time").text)
    val l = 2.0d :: 3.0d :: Nil
    val func: PartialFunction[List[Double], Double] = { case List(x, y) => x + y }
    
    type Op = PartialFunction[List[Double], Double]
    val operations = new collection.mutable.HashMap[String, Op]
    operations += (
      "add"  -> func
    )
    
    operations("add")(l)
    
    def parse = (elem('t')~elem('r')~elem('u')~elem('e')) | 
                (elem('f')~elem('a')~elem('l')~elem('s')~elem('e'))
    println(parse)
  }
}
