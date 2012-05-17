package org.test

object App extends TestApp {
  override def m = { println("Init String"); new String("Test") }
}


abstract class TestApp {
  def m: String
  
  def main(args: Array[String]) {
    println("Running: " + m)
  }
}
