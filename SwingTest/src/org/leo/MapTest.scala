package org.leo

object MapTest {
  
  def main(args: Array[String]) {
    val m = Map(1 -> List(1,2,3,4), 2 -> List(2,3,5,7), 3 -> List(5,6,7,9))
    val k = 2
    val nml = m.filter({ case (k, _) => k != 2 })
               .toList.map(t => if (t._1 > k) (t._1 - 1, t._2) else t)
    println(Map[Int, List[Int]]() ++ nml)
  }

}
