package net.euler {
  object Problem26 {
    def isRecurring(): Boolean = {
      true
    }
    
    def main(args: Array[String]) = {
      for( i <- 1 to 1000) {
        println("i: " + i)
        println("1/" + i + " = " + 1.0 / i)
      }
    }
  }
}
