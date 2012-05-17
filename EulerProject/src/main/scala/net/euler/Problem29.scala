package net.euler

object Problem29 {
  import math.IntMathUtil._
  import Math._
  def main(args: Array[String]) {
    val upper = 100
    val lower = 2
    val div = List.range(2, (upper * 6) + 1).map( x => getDivisorsWithout1(x).filter(isPrime _))
    var counter = 0
    for{i <- List.range(lower, floor(sqrt(upper)).toInt + 1);
        l <- div} {
      counter += l.filter(pow(i, _) <= upper).size
    }
    println(counter)
    println(pow((upper - lower + 1), 2).toInt - counter)
  }
}
