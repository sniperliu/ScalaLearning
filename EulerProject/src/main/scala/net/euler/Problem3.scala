package net.euler

object Problem3 extends Application {
  
  // Assume that max P Factor is still within limit
  def isPrime(num: Int) = num > 1 && !List.range(2, num / 2 + 1).exists(num % _ == 0)
  
  def getMaxPrimeFactor(num: BigInt) = {
      def getMaxPFactor1(factor: BigInt, num: BigInt): BigInt = {
        if (factor > num / 2) num
        else {
          if (num % factor == 0 && isPrime(factor.intValue)) getMaxPFactor1(2, num / factor)
          else getMaxPFactor1(factor + 1, num)
        }
      }
      getMaxPFactor1(2, num)
  }

  // println(getMaxPrimeFactor(BigInt("600851475143")))
}
