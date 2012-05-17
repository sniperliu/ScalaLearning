package net.euler

object Problem25 {
  
  var noOfTerm = 2;
  def Fibo(a: BigInt, b: BigInt): BigInt = {
    if (b.toString.length >= 1000) a else { noOfTerm += 1; Fibo(b, a + b) }  
  }
  
  def main(args: Array[String]) {    
    println(Fibo(1, 1))
    println(noOfTerm)
  }
}
