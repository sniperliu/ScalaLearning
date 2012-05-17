package org.test.actor

import se.scalablesolutions.akka.actor.Actor
// import Actor._

class MyActor extends Actor {
  
  def receive = {
    case "test" => println("Received Test")
    case _ => println("Unknown Message")
  }
  
}

object AkkaTest {

  def main(args: Array[String]) {
//    val myActor = actor {
//      case "test" => println("Received Test")
//      // case _ => println("Unknown Message")
//    }
    val myActor = new MyActor
    myActor.start
    
    myActor !! "test"
  }
  
}
