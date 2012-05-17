package net.euler

object Problem42 {
  def isTriangleNum(v: Int) = {
    import Math.sqrt
    val n = sqrt(2 * v + 0.25) - 0.5
    n == n.toInt
  }
  
  def main(args: Array[String]) {
    import scala.io.Source
    val src = "C:/Users/hl26184/workspace/EulerProject/data/words.txt"
    val words = Source.fromFile(src).getLine(1).split(",").map(_.replaceAll("^\"|\"$", ""))
    val wValue = words.map(str => (str, str.toList.map(_.toInt - 'A'.toInt + 1).foldLeft(0)(_ + _)))
    // wValue.filter(grp => isTriangleNum(grp._2)).foreach(println _)
    println(wValue.filter(grp => isTriangleNum(grp._2)).size)
  }
}
