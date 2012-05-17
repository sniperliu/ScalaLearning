package net.euler

object Problem48 {
  def main(args: Array[String]) {
    val nums = List.range(1, 1001).map( x => BigInt(x).pow(x)).reduceLeft(_ + _)
    println(nums.toString.toList.takeRight(10).mkString)
  }
}
