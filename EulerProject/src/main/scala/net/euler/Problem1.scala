package net.euler

object Problem1 extends Application {
  val result = (1 to 999).filter(x => x % 3 == 0 || x % 5 == 0)
                      .reduceLeft(_ + _)
  println("Result for Problem1: " + result)
}
