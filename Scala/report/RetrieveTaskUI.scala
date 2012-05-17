package com.citi.gta.report

import scala.io._

import com.citi.gta.util.ResUtil._

object RetrieveTaskUI {
  
  def getTaskUI(className: String): String = {
    try {
        val c = Class.forName(className)
        val str = c.getMethod("getTaskUI").invoke(c.newInstance) match {
          case s: String => s
          case null => "null"
          case _ => "Error"
        }
        className + ": " + str
    } catch {
      case e => className + ": Exception"
    }

  }

  def main(args: Array[String]): Unit = {
    val f = "C:/Users/hl26184/Desktop/getTaskUIJava.txt"
    Source.fromFile(f).getLines.map((s: String) => getTaskUI(s.trim)).foreach(println)
  }

}