package net.euler

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.{Map, HashMap}

object Problem12 {
  
  // Assume that max P Factor is still within limit
  def isPrime(num: Int) = num > 1 && !List.range(2, Math.sqrt(num) + 1).exists(num % _ == 0)
  
  def findNPrimes(n: Int) = {
    val array = new Array[Int](n)
    var num = 2;
    var index = 0;
    
    while(index < n) {
      if (isPrime(num)) { array(index) = num; index = index + 1 }
      num = num + 1
    }
    
    array.toList
  }
  
  def divNumber(n: Long, primes: List[Int]) = {
    val map = new HashMap[Int, Int]();
    for (i <- primes) {
      map(i) = 0
    }
    
    var test = n
    for (i <- primes if (i <= n)) {
      var numOfFactors = 0;
      while (test % i == 0) {
        numOfFactors = numOfFactors + 1
        test = test / i
      }
      if (numOfFactors > 0) map(i) = numOfFactors
    }
    
    var total = 1
    map.foreach( kv => if (kv._2 > 0) total = total * (kv._2 + 1) )
    println(n + " -> " + total)
    total
  }
  
  def main(args: Array[String]) {
    // Init
    val primes = findNPrimes(500)
    
    var tri: Long = 1
    var num = 2
    while(divNumber(tri, primes) < 500) {
      tri = tri + num
      num = num + 1      
    }
    println(tri)
  }
}
