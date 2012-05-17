package net.euler

object Problem30 {
  def main(args: Array[String]) {
    import Math._
    val l = List.range(2, 100000)
    val results = l.filter( n => n.toString
                                  .toList.map(x => pow(x.toString.toInt, 5).toInt)
                                  .reduceLeft(_ + _) == n)
    println(results.reduceLeft(_ + _))
  }
}
