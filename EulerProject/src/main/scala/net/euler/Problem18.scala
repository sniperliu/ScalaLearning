package net.euler

object Problem18 {
  
  def getTriangle(filename: String) = {
    import scala.io._
    val src = Source.fromFile(filename)
    val lines = src.getLines.map(line => line.split(" ").map(str => str.trim toInt)) 
    lines.toList.toArray
  }
  
  def getEdgeValue(triangle: Array[Array[Int]], topV: (Int, Int), right: Boolean) = {
    val vs = 
      if (right) List.range(topV._1, triangle.size - 1).zip(List.range(topV._2, triangle.size - 1))
      else List.range(topV._1, triangle.size - 1).map(x => 0).zip(List.range(topV._2, triangle.size - 1))
    var value = 0
    for(v <- vs) {
      value += triangle(v._1)(v._2)
    }
    value
  }
  
  def findMaxPath(triangle: Array[Array[Int]]): Int = {
    def findMax(topV: (Int, Int)): Int = {
      if (topV._1 == triangle.size - 1) triangle(topV._1)(topV._2)
      else findMax((topV._1 + 2, topV._1 + 2))
    }
    
    findMax((0, 0))
  }
  
  def main(args: Array[String]) = {
    val dataFile = "C:\\Users\\hl26184\\workspace\\EulerProject\\data\\Problem18.dat";
    val triangle = getTriangle(dataFile)
    println(getEdgeValue(triangle, (0, 0), false))
    // findMaxPath(triangle)
  }
  
}
