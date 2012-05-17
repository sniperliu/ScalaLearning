package org.test.actor

import scala.actors._
import Actor._

object ActorTest {

  def main(args: Array[String]) {
    val myActor = actor {
      loop {
        react {
          case "test" => println("received test")
          case _ => println("received unknown message"); exit
        }
      }
    }
    
     myActor ! "test"
     myActor ! ActorTest
  }
  
}

//class MyActor extends Actor {
//  def receive() = {
//    case "test" => println("received test")
//    case _ => println("received unknown message")
//  }
//}
