package net.euler

import scala.io.Source

object Problem13 {
  def main(args: Array[String]) {
    val zCode = '0'
    val datafile = "C:/Users/hl26184/workspace/EulerProject/data/Problem13.dat"
    val src = Source.fromFile(datafile)
    val lines = src.getLines.map( line => line.trim.map(_ - zCode).toArray )
    val matrix = new Array[Array[Int]](100)
    lines.copyToArray(matrix, 0)
    
    val sum = new Array[Int](54)
    for (i <- List.range(0, 50).reverse) {
      var num = 0
      for (j <- List.range(0, 100)) {
        num = num + matrix(j)(i) + sum(49 - i)
      }     
      println("num: " + num)
      sum(49 - i) = num % 10
      val temp1 = sum(50 - i) + (num % 100) / 10
      sum(50 - i) = temp1 % 10
      val temp2 = sum(51 - i) + num / 100 + temp1 / 10
      if (temp2 > 10) {
        sum(52 - i) = temp2 / 10
      }
      sum(51 - i) = temp2 % 10
    }
    
    println("SUM: " + sum.reverse)
  }
}
