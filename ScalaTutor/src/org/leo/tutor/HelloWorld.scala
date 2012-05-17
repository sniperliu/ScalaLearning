package org.leo.tutor

object HelloWorld {
  def main(args : Array[String]) {
    val l = 6.0d :: 3.0d :: Nil
    val func: PartialFunction[List[Double], Double] = { case List(x, y) => x + y }
    
    type Op = List[Double] => Double // PartialFunction[List[Double], Double]
    val operations = new collection.mutable.HashMap[String, Op]
    operations += (
      "add"  -> func // { case List(x, y) => x + y }
    )
    
    System.out.println(operations("add")(l))
  }
}
