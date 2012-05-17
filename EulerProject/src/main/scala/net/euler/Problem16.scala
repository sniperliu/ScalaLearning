package net.euler

object Problem16 {
  def main(args: Array[String]) = {
    var number = Array.range(1, 400).map(x => 0)
    number(0) = 1
    var column = 1
    
    def multiply2(number: Array[Int]) = {
      var temp = 0
      for (i <- List.range(0, column)) {
        var factor = number(i) * 2
        if (temp > 0) {factor = factor + temp; temp = 0}
        if (factor >= 10) {temp = factor / 10; number(i) = factor % 10} 
        else number(i) = factor        
      }
      if (temp > 0) {column = column + 1; number(column - 1) = temp}
    }
    
    for (i <- List.range(0, 1000)) multiply2(number)
    
    println("Sum: " + number.reverse.toString)
    println("Sum: " + number.foldLeft(0)((x, y) => x + y))
  }
}
