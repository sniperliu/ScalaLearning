package net.euler

import Math._

object Problem40 {
  
  /**
   * Get the number according to the position
   * in irrational Decimal
   */
  def getNumInIrrationalDecimal(position: Int) = {
    val l = List(1, 2, 3, 4, 5, 6)
    val t1 = l.map(pow(10, _).toInt - 1)
    val t2 = t1.zip(0 :: t1.dropRight(1)).map(x => x._1 - x._2).zip(l).map(x => (x._2, x._1 * x._2))
    
    def findNum(count: Int, repr: List[Tuple2[Int, Int]]): Int = {
      val t = repr.head
      if (count < t._2) {
        val c = if (count % t._1 == 0) count / t._1 - 1 else count / t._1
        val charOfInt: Char = (((pow(10, t._1 - 1).toInt + c).toString.toArray)((count % t._1 + t._1 - 1) % t._1))
        charOfInt.toString.toInt
      } else findNum(count - t._2, repr.tail)
    }
    
    findNum(position, t2)
  }
  
  def main(args: Array[String]) {
    val list = List.range(1, 7).map(pow(10, _).toInt).map(getNumInIrrationalDecimal _)
    println(list)
  }
}
