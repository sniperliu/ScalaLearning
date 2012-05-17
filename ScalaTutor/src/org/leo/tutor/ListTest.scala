package org.leo.tutor

object ListTest extends Application {
  def squareList1(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => y * y :: squareList1(ys)
  }
  
  def squareList2(xs: List[Int]): List[Int] = xs map (x => x * x)
  
  val list = 1 :: 2 :: 3 :: 4 :: 5 :: Nil
  
  println(squareList1(list))
  
  println(squareList2(list))
  
  list.foreach(x => println(x * x))
}
