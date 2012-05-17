package org.test.jmx

trait HelloMBean {
  
  def sayHello(): Unit
  def add(x: Int, y: Int): Int
  
  def getName(): String
  
  def getCacheSize(): Int
  def setCacheSize(size: Int): Unit
  
}
