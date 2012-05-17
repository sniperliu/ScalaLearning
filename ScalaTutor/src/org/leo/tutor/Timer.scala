package org.leo.tutor

object Timer {
  
  def oncePerSec(callback : () => unit) {
    while(true) { callback(); Thread sleep 1000}
  }
  
  def timeFires() {
    println("time flies like an arrow...")
  }
  
  def main(args : Array[String]) {
    oncePerSec(() => println("Leo, time flies like an arrow..."))
  }
}
