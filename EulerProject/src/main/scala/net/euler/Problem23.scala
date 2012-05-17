package net.euler

object Problem23 {
  import math.IntMathUtil.getDivisors
  
  def isAbundant(n: Int) = 
    getDivisors(n).reduceLeft(_ + _) > n
  
  def getAbundantIntWithin(limit: Int) = 
    List.range(2, limit).filter(isAbundant _)
  
  def toSet[T](list: List[T]) = {
    def traverse(list: List[T])(set: Set[T]): Set[T] = list match {
      case hd :: tail => traverse(tail)(set + hd)   // create a new Set, adding hd
      case Nil => set
    }
    traverse(list)(Set[T]())
  }
  
  def main(args: Array[String]) {
    import scala.compat.Platform.currentTime 
    val start = currentTime
    val upper = 28123
    val abundant = getAbundantIntWithin(upper)
    val total = List.range(1, upper + 1).reduceLeft(_ + _)
    var reduce = 0
    import scala.collection.mutable.{Set}
    val nums = Set(0)
    for (a <- abundant) {
      nums ++= abundant.filter( _ >= a).map(_ + a).filter( _ <= upper )
    }
    println(total - nums.reduceLeft(_ + _))
    println(currentTime - start)
  }
}
