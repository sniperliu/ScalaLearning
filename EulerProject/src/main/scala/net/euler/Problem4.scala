package net.euler

object Problem4 extends Application {
  def maxPalindromeBy3() = {
    var temp = 0
    var list: List[Int] = Nil
    for {
      i <- List.range(100, 1000).reverse
      j <- List.range(100, 1000).reverse
    } {
      temp = i * j
      if (temp.toString.trim == temp.toString.reverse.trim) list = temp :: list
    }
    list.reduceLeft( (x, y) => if (x > y) x else y )
  }

  println(maxPalindromeBy3)
}
