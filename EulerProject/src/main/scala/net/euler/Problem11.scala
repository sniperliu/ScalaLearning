package net.euler

import scala.io.Source

object Problem11 {
  // val matrix = new Array[Int](20)
  def main(args: Array[String]) {
    val datafile = "C:/Users/hl26184/workspace/EulerProject/data/Problem11.dat"
    val src = Source.fromFile(datafile)
    val lines = src.getLines.map(line => line.split(" ").map(str => str.trim toInt))    
    val matrix = new Array[Array[Int]](20)
    lines.copyToArray(matrix, 0)

    var max = 0
//    // Row Scan
//    for {i <- List.range(0, 20)
//         j <- List.range(0, 20 - 3)} {
//      val prod = matrix(i)(j) * matrix(i)(j + 1) * matrix(i)(j + 2) * matrix(i)(j + 3)
//      if ( max < prod) max = prod
//    }
//    // Column Scan
//    for {i <- List.range(0, 20)
//         j <- List.range(0, 20 - 3)} {      
//      val prod = matrix(j)(i) * matrix(j + 1)(i) * matrix(j + 2)(i) * matrix(j + 3)(i)
//      if ( max < prod) max = prod
//    }
//    // Back Slash
//    // Dia Scan(Up Half)
//    for {i <- List.range(0, 20 - 3) 
//         j <- List.range(0, 20 - 3 - i)} {        
//      val prod = matrix(j)(i + j) * matrix(j + 1)(i + j + 1) * matrix(j + 2)(i + j + 2) * matrix(j + 3)(i + j + 3)    
//      if ( max < prod) max = prod
//    }
//    // Dia Scan(Down Half)
//    for {i <- List.range(0, 20 - 3) 
//         j <- List.range(0, 20 - 3 - i)} {                 
//      val prod = matrix(i + j)(j) * matrix(i + j + 1)(j + 1) * matrix(i + j + 2)(j + 2) * matrix(i + j + 3)(j + 3)    
//      if ( max < prod) max = prod
//    }
    
    val matrixR = new Array[Array[Int]](20)
    for(i <- List.range(0, 20)) {
      matrixR(i) = matrix(i).reverse
    }
        // Back Slash
    // Dia Scan(Up Half)
    for {i <- List.range(0, 20 - 3) 
         j <- List.range(0, 20 - 3 - i)} {        
      val prod = matrixR(j)(i + j) * matrixR(j + 1)(i + j + 1) * matrixR(j + 2)(i + j + 2) * matrixR(j + 3)(i + j + 3)    
      if ( max < prod) max = prod
    }
    // Dia Scan(Down Half)
    for {i <- List.range(0, 20 - 3) 
         j <- List.range(0, 20 - 3 - i)} {                 
      val prod = matrixR(i + j)(j) * matrixR(i + j + 1)(j + 1) * matrixR(i + j + 2)(j + 2) * matrixR(i + j + 3)(j + 3)    
      if ( max < prod) max = prod
    }
    println(max)
  }
}
