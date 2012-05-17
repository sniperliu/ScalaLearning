package org.leo.tutor

object FactorialTest {
  def factorial(n: Int): Int = if (n == 0) 1 else n * factorial(n - 1) 
  
  def factorialTail(n: Int): Int = {
    def factorial2(n: Int, factor: Int): Int = if (n == 0) factor else factorial2(n - 1, n * factor)
    factorial2(n, 1)
  }
  
  def main(args: Array[String]) {
    println(factorial(10))
    println(factorialTail(10))
  }
}
