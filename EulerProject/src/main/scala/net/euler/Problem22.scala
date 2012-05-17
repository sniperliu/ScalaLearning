package net.euler

object Problem22 {
  def main(args: Array[String]) {
    
    def sumStr2AlphaOrder(str: String) = 
      str.toList.map( c => c.toInt - 'A'.toInt + 1).reduceLeft(_ + _)
    
    import scala.io._
    val datafile = "C:/Users/hl26184/workspace/EulerProject/data/names.txt"
    val items = Source.fromFile(datafile).getLine(1).split(",")
    val names = items.map( item => item.replaceAll("\"", "")).toList
    val sortedNames = names.sort( (a, b) => a.compareTo(b) < 0)
    val sum = sortedNames.map( sumStr2AlphaOrder _ ).zipWithIndex.map( group => group._1 * (group._2 + 1)).reduceLeft(_ + _)
    println(sum)
  }
}
