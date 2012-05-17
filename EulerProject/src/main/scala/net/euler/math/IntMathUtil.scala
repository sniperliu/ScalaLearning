package net.euler.math

object IntMathUtil {
  
  /**
   * The method is used to check an integer is prime or not.
   * The logic follow below facts
   * a. 1 is not a prime.
   * b. All primes except 2 are odd.
   * c. All primes greater than 3 can be written in the form 6k+/-1.
   * d. Any number n can have only one primefactor greater than n .
   * The consequence for primality testing of a number n is: if we cannot find a number f less than
   * or equal n that divides n then n is prime: the only primefactor of n is n itself
   * 
   * @see http://projecteuler.net/project/resources/007_c1bfcd3425fd13f6e9abcfad4a222e35/007_overview.pdf
   */
  def isPrime(n: Int) = {
    import Math._
    if (n <= 0) false
    else         
      if (n == 1) false
        else 
          if (n < 4) true
          else 
            if (n % 2 == 0) false 
            else 
              if (n < 9) true
              else 
                if (n % 3 == 0) false
                else 
                  if (List.range(5, floor(sqrt(n)).toInt + 1, 6)
                          .exists(r => n % r == 0 || n % (r + 2) == 0)) false
                  else true
  }
  
  def getPrimesWithin(limit: Int) = {
    val candidates = List.range(1, limit + 1, 2)
    candidates.filter(isPrime _)
  }
  
  def getDivisors(n: Int) = 1 :: getDivisorsWithout1(n)
  
  def getDivisorsWithout1(n: Int) = {
    import Math._
    val candidates = List.range(2, floor(sqrt(n)).toInt + 1)
    val divisors = candidates.filter(n % _ == 0)
    divisors ::: divisors.filter(x => x * x != n).map(n / _)
  }

  def main(args: Array[String]) {
     println(getDivisors(260))
  }
}
