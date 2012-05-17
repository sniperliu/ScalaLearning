package net.euler

object Problem15 extends Application {
  
  val size = 20
  
  import scala.collection.mutable._
  var grid: Array[Array[Long]] = Array.range(0, size + 1).map(x => Array.range(0, size + 1).map(x => 0))
  
  def spider(x: Int, y: Int, factor: Int): Unit = {
    if (x == 0 && y == 0) return
    else {
      if (x > 0) {grid(x - 1)(y) = grid(x - 1)(y) + factor; spider(x - 1, y, factor);} 
      if (y > 0) {grid(x)(y - 1) = grid(x)(y - 1) + factor; spider(x, y - 1, factor);}
    }
  }
  
  for (y <- List.range(0, size + 1)) grid(size)(y) = 1
  for (x <- List.range(0, size + 1)) grid(x)(size) = 1
  
  for (x <- List.range(0, size).reverse;
       y <- List.range(0, size).reverse) {
    grid(x)(y) = grid(x + 1)(y) + grid(x)(y + 1)
  }
  
  grid.foreach(x => println(x.toString))
  println(grid(0)(0))
}
