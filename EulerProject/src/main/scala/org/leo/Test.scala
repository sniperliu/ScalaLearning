pakcage org.leo {

	trait MyAbsContainer {
	  type T >: Seq;
	  val container: T
	  
	  def showAsString = T.mkString(",")
	}
	
	class MyListContainer(val limit: Int) extends MyAbsContainer[List] {
	  val container = List.range(1, limit)
	  
	  def toString = "My List Container: " + showAsString
	}
	
	object App extends Application {
	  val t = MyListContainer(9)
	  
	  println(t.toString)
	}
}
