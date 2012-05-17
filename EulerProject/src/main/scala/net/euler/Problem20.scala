package net.euler

object Problem20 {
  def mul(n: BigInt): BigInt = 
    if (n == 1) 1
    else n * mul(n - 1)
  
  def sum(n: BigInt): BigInt = {
    if (n > 0) n % 10 + sum(n / 10)
    else 0
  }
  
  def main(args: Array[String]) {
    val a: BigInt = sum(mul(100))
    println(a)
  }
}
