package org.leo.gclog

import java.io._
import java.util.regex._

import scala.io.Source

object GCLogAnalyze {
  
  val patternGC = Pattern.compile("""^\d+\.\d+: \[GC.*""")
  val PartialGC = """\d+\.\d+:\s\[GC\s(\d+\.\d+):\s\[.+\]\s+(\d+)K->(\d+)K\((\d+)K\),\s+(\d+\.\d+)\s+secs\]""".r

  /**
   * 
   */
  def parse(str: String) = str match {
    case PartialGC(time, sizeBefore, sizeAfter, sizeTotal, timeSpent) =>
      Some((time.toDouble, sizeBefore.toInt, sizeAfter, sizeTotal, timeSpent))
    case _ => None
  }
  
  def isPartialGCLine(str: String): Boolean =     
    patternGC.matcher(str).matches

  def main(args: Array[String]) {
    val fileName = ".//data//log.stdout"
    val input = Source.fromFile(fileName)
    
    // println( isGCLine("117.037: [GC 117.037: [ParNew: 329024K->10239K(339264K), 0.3067900 secs] 355069K->47047K(1038336K), 0.3069396 secs]") )
    
    // input.getLines.map(_.trim).filter(isPartialGCLine(_)).foreach( println(_))

    // output(".//data//log.filter", input.getLines.map(_.trim).filter(isGCLine(_)))
  
    parse("224000.272: [GC 224000.272: [ParNew: 329024K->329024K(339264K), 0.0000603 secs]224000.272: [Tenured: 669369K->673236K(699072K), 10.0802176 secs] 998393K->673236K(1038336K), 10.0805324 secs]")
  }
  
  def output(file: String, lines: Iterator[String]) {
    val writor = new PrintWriter(new FileWriter(file))
    try {      
      lines.foreach(writor.println(_))
    } catch {
      case e => println("Error: " + e.getMessage)
    } finally {
      writor.close
    }
  }
  
}
