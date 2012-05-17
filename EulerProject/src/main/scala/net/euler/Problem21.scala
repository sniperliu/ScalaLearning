package net.euler

/**
 * Refre Problem 12
 */
object Problem21 {
  
  import math.IntMathUtil.getDivisors
  
  def sum(iter: Seq[Int]) = iter.reduceLeft(_ + _)
  
  def getAmicablePair(a: Int) = {
    val b = sum(getDivisors(a))
    if ( a != b && sum(getDivisors(b)) == a ) (a, b)
    else null
  }
  
  def isAmicableNum(a: Int) = {
    val b = sum(getDivisors(a))
    if ( a != b && sum(getDivisors(b)) == a ) true
    else false
  }  
  
  def main(args: Array[String]) = {
    println(sum(List.range(1, 10001).filter(isAmicableNum _)))
  }
}
