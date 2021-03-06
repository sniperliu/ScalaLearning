package org.leo

object AlgebraTest {
  
  val Π = Math.Pi
  
  def √(x:Double)=Math.sqrt(x)  
  def ∑(r:Range)(f:Int =>Int)=r.reduceLeft(_+ f(_))
  def ∏(r:Range)(f:Int =>Int)=r.reduceLeft(_* f(_))

  def main(args: Array[String]) {
    println(√(9))
    println("Π is " + Π)
    println(∑(1 to 100)(x => x^2))
  }
}
