package org.test.jmx

import java.lang.management._

import javax.management._

object Main {
  def main(args: Array[String]) {
    val mbs: MBeanServer = ManagementFactory.getPlatformMBeanServer()
    val name: ObjectName = new ObjectName("org.test.jmx:type=hello")
    val mbean: Hello = new Hello(500)
    mbs.registerMBean(mbean, name)
    
    println("Waiting forever")
    Thread.sleep(Math.MAX_LONG)
  }
}
