package com.citi.gta

import scala.compat.Platform.currentTime
import scala.io.Source

object LineOutput {
  
  val executionStart: Long = currentTime
  
  def zfill(str: String, length: Int): String = {
    val gap = length - str.length 
    if (gap > 0) "'" + List.range(0, gap).map(_ => "0").mkString + str + "'"
    else "'" + str + "'"
  }
  
  def reOrgLine(f: String => String)(source: List[String], n: Int) = {
    
    def joinHeadN(line: Int, src: List[String], res: String): String = {
      if (src.take(n).length == 0) res 
      else {
        val append = if (line % 100 == 0) ")\nselect emp_name, emp_status from workbrain.employee where emp_name in (\n" else ""
        joinHeadN(line + 1, src.drop(n), res + append + src.take(n).map(zfill(_, 10)).mkString("", ", ", ",\n"))
      }
    }
        
    val output = joinHeadN(0, source.map(f), "")
    output.slice(0, output.length - 2)
  }
  
  def main(args: Array[String]) = {
    if (args.length > 1) {
      val source = Source.fromFile(args(0)).getLines.toList
      val target = new java.io.FileWriter(args(1))
      target.write(reOrgLine((a: String) => a.trim)(source, 10))
      target.close
    } else {
      println("Usage: scala LineOutput src target")
    }
    val total = currentTime - executionStart   
    println("[total " + total + "ms]")
  }
}
