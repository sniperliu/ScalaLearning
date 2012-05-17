package org.test.jmx

class Hello(var cacheSize: Int) extends HelloMBean {  
  
  private val DEFAULT_CACHE_SIZE = 200
  cacheSize = DEFAULT_CACHE_SIZE
  
  def sayHello() {
    println("hello, world")
  }
  
  def add(x: Int, y: Int): Int = {
    x + y
  }
  
  def getName(): String = "Reginald"
  
  def getCacheSize = cacheSize
  def setCacheSize(size: Int) {
    this.cacheSize = size
  }

}
