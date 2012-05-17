package net.euler

import net.euler.math.IntMathUtil._

object Problem27 {
  
  def f(a: Int, b: Int)(n: Int) = n * n + a * n + b
  
  def checkConsPrime(f: Int => Int, l: List[Int], count: Int): Int = {
    // println(l.head + " " + " " + f(l.head) + isPrime(f(l.head)))
    if (!isPrime(f(l.head))) count
    else checkConsPrime(f, l.tail, count + 1)
  }
  
  def main(args: Array[String]) {
    val ns = List.range(0, 80)
    val as = List.range(-1000, 1001)
    val bs = 1 :: List.range(1, 1001).filter(isPrime(_)).filter( _ > 41 )

    var l = (0, 0, 0) :: Nil
    for {
      a <- as;
      b <- bs
    }  {
      val c = checkConsPrime(f(a, b) _, ns, 0)
      if (c > 1) l = (a, b, c) :: l
    }
    
    val result = l.reduceLeft((x, y) => if (x._3 > y._3) x else y )
    println(result._1 * result._2)
  }
}
