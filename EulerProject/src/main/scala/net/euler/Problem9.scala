package net.euler

import scala.Math._

object Problem9 extends Application {
  // Get all Pythagorean
  def getPythTriple(max: Int) = {
    var list: List[(Int, Int, Int)] = Nil
    for {
      a <- List.range(1, max)
      b <- List.range(1, max).reverse if (b > a)
    } {
      val c2 = a * a + b * b
      var c = sqrt(c2)
      if (c * c == c2) list = (a, b, c) :: list
    }
    
    list
  }
  
  println(getPythTriple(1000))
  // println(getPythTriple(1000).filter(t => t._1 + t._2 + t._3 == 1000))
  println(Long.MaxValue)
}
