package org.leo

object SafeDivide {
  def safeDivide(m1: Option[Int])(m2: Option[Int]): Option[_] = 
    for {
      val x <- m1;
      val y <- m2
    } yield {
      if (y <= 0) None else Some(x / y)
    }
  
  def main(args: Array[String]) {
    println(safeDivide(Some(5))(Some(0)))
  }
}
