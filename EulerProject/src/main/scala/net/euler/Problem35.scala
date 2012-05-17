package net.euler

object Problem35 {
  def isCircularPrime(n: Int): Boolean = {
    import math.IntMathUtil.isPrime
    def check(x: String): Boolean = 
      x.toList match {
        case head :: tail => 
          val strNum = (tail ::: head :: Nil).mkString
          val newNum = strNum.toInt
          if (newNum == n) true
          else 
            if (!isPrime(newNum)) return false
            else check(strNum)
        case _ => isPrime(x.toInt)
      }
    if (!isPrime(n)) false
    else check(n.toString)
      
  }
  
  def main(args: Array[String]) {
    import scala.compat.Platform.currentTime   
    val start = currentTime
    val tmp = List.range(3, 1000000, 2).filter(isCircularPrime _)
    println((2 :: tmp).size)
    println(currentTime - start)
  }
}
