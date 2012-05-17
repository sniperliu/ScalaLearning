package net.euler

import Problem3.{isPrime}
import scala.Math._

object Problem5 extends Application {
  def primesWithin(max: Int) = {
    List.range(2, max).filter(isPrime(_))
  }
  
//  case class Num(x: Int)
//  case class Prime(x: Int)
//  case class Combine(x: Prime, y: Num)
//  def primeFactors(num: Int): List[Int] = {
//    val list = primesWithin(num / 2)
//    num match {
//      case Combine(Prime(2), Num(num / 2)) if (num % 2 == 0) => primeFactors(j) :: List(2)
//    }
//  }

  val primes = primesWithin(20)
  def primeFactors(num: Int) = {
    var factors: List[Int] = Nil
   
    var p = primes.head
    def primeFactors1(num: Int, prms: List[Int]) { 
      if (num > 1) 
        prms match {
          case p :: tail if (num > 1 && num % p == 0)=> 
            factors = p :: factors
            primeFactors1(num / p, prms)
          case p :: tail  => primeFactors1(num, tail)
          case Nil => return
        }
    }
    primeFactors1(num, primes)
    factors
  }
  
  List.range(2, 21).map(primeFactors(_))
}
