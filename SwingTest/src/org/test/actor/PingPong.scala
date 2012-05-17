package org.test.actor

import scala.actors.Actor
import Actor._

case object Ping
case object Pong
case object Stop

class Ping(count: Int, pong: Actor) extends Actor {
  
  def act() {
    var pingLeft = count - 1
    pong ! Ping
    while(true) {
      receive {
        case Pong =>
          if (pingLeft % 1000 == 0) println("Ping: pong")
          if (pingLeft > 0) {
            pong ! Ping
            pingLeft -= 1            
          } else {
            println("Ping: stop")
            pong ! Stop
            exit
          }
      }
    }
  }
  
}

class Pong extends Actor {
  
  def act() {
    var pongCount = 0
    loop {
      react {
        case Ping => 
          if (pongCount % 1000 == 0) println("Pong: ping " + pongCount)
          sender ! Pong
          pongCount += 1
        case Stop => 
          println("Pong: Stop")
          exit
      }
    }
  }
}

object PingPong extends Application {
  val pong = new Pong
  val ping = new Ping(100000, pong)
  pong.start
  ping.start
}
