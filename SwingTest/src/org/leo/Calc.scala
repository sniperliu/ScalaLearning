package org.leo

object Calc extends Application {
  
  def mean(n: Double, m: Double): Double = {
    def go(s: Double, i: BigInt, x: Double): Double = {
      if ( x > m ) s / i.doubleValue
      else go(s + x, i + 1, x + 1)
    }
    
    go(0, 0, n)
  }
  
  println(mean(1, 1e9))

}
