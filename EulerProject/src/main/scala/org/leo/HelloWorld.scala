package org.leo

class HelloWorld extends IOApplication {
  import IOAction._  
  import RTConsole._  
     
  def sayHello(n:String) = n match {   
    case "Bob" => putLine("Hello, Bob")   
    case "Chuck" => putLine("Hey, Chuck")   
    case "Sarah" => putLine("Helloooo, Sarah")   
    case _ => fail("match exception")   
  }   
     
  def ask(q:String) =  
    putString(q) >> getString   
  
  def processString(s:String) = s match {   
    case "quit" => putLine("Catch ya later")   
    case _ => (sayHello(s) or            
        putLine(s + ", I don't know you.")) >>   
        loop    
  }   
       
  val loop:IOAction[Unit] =    
    for {   
      name <- ask("What's your name? ");   
      _ <- processString(name)   
    } yield ()   
     
  def iomain(args:Array[String]) = {   
    putLine("This is an example of the IO monad.") >>   
    putLine("Enter a name or 'quit'") >>   
    loop   
  }
}

object Hello {
  def main(args: Array[String]) {
    new HelloWorld().main(args)
  }
}
