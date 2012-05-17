package org.leo.algorithm

object Algorithm {
  
  def qsort1[A <% Ordered[A]](xs: List[A]): List[A] = {
    if (xs.length == 0) xs
    else {
      val p = xs(xs.length / 2)
      qsort1(xs.filter(_ < p)) ++
      xs.filter(_ == p) ++
      qsort1(xs.filter(_ > p))
    }
  }
  
  def qsort2[A <% Ordered[A]](lt: List[A]): List[A] = 
    lt match {
      case Nil => Nil
      case x :: xs => 
        qsort2(xs.filter(_ < x)) ++ List(x) ++ qsort2(xs.filter(_ >= x))
    }

  def main(args: Array[String]) {
    println(qsort2(Nil))
    println(List(1).filter(_ < 1))
    println(qsort2(List(1,5,3,1)))
  }
  
}
