package org.leo.util

import _root_.scala.actors._
import _root_.scala.util.logging.Logged
import Actor._

case class Log(msg: String)

trait ActorLogger extends Logged {
  
  override def log(msg: String) {
    ActorLogger.logger ! Log(msg)
  }

}

object ActorLogger {
  
  def logger = actor {
    loop {
      react {
        case Log(msg) => println(msg)
      }
    }
  }
  
}

object LoggerTest extends ActorLogger {
  
  def main(args: Array[String]) = {
    log("Hello, World")
    log("Test")    
  }
  
}
