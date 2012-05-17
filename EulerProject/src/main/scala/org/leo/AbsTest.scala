package org.leo {

	trait MyAbsContainer[T, C <: Seq[T]] {
	  // type T
	  val container: C
	  
	  def showAsString = container.mkString(",")
	}
	
	class MyListContainer(val limit: Int) extends MyAbsContainer[Int, List[Int]] {
	  val container = List.range(1, limit)
	  
	  override def toString = "My List Container: " + showAsString
	}
	
	object App extends Application {
	  val t = new MyListContainer(9)
	  
	  println(t.toString)
	}
}
