package net.euler

object Problem2 extends Application {
  val max = 4000000
  
  def Fibo(a: BigInt, b: BigInt): BigInt = {
    if (b > max) a else Fibo(b, a + b)  
  }
  
  var sum: BigInt = 0
  def FiboSum(a: BigInt, b: BigInt): BigInt = {
    if (b > max) sum else FiboSum(b, a + b) + (if (b % 2== 0) b else 0)
  }
  
  def FiboSum1(a: BigInt, b: BigInt, sum: BigInt): BigInt = {
    if (b > max) sum else FiboSum1(b, a + b, sum + (if (b % 2 == 0) b else 0))
  }
  
  println(FiboSum(1,2))
  // println(FiboSum1(1, 2, 0))
}
