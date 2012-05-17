package net.euler

object Problem14 extends Application {

    val limit = 1000000
    var length = 0
    var max = (1, 1)
    
    def seqLength(n: Long):Int = {
      if (n == 1) {length = length + 1; length}
      else {
        if (n % 2 == 0) {length = length + 1; seqLength(n / 2)} 
        else {length = length + 1; seqLength(3 * n + 1)}
      }
    }
    
    var number = 1
    while (number <= limit) {
      length = 0
      val len = seqLength(number)
      if (len > max._2) max = (number, len)
      number = number + 1
    }
    println("Number: " + max._1 + ", Length: " + max._2)
}
