package net.euler

object Problem28 {
  def main(args: Array[String]) {
    val dims = List.range(1, 1002, 2)
    val top = dims.flatMap(dim => { dim * dim - 3 * (dim - 1) :: 
                           dim * dim - 2 * (dim - 1) ::
                           dim * dim - (dim - 1) ::
                           dim * dim :: Nil})
    println(top.reduceLeft(_ + _) - 3)
  }
}
