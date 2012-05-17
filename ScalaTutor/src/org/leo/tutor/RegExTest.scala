package org.leo.tutor

import scala.util.matching._

object RegExTest {
  def main(args: Array[String]) = {
    val LogEntry = new Regex("""Completed in (\d+)ms \(View: (\d+), DB: (\d+)\) \| (\d+) OK \[http://app.domain.com(.*)\?.*""")
    val line = "Completed in 100ms (View: 25, DB: 75) | 200 OK [http://app.domain.com?params=here]"
    
    val HSumEntry = """(REG|REG1.0|OT1.0|OT1.25|OT1.35|OT1.5|OT1.6|HOL-1|HOL-2|HOL-NT|OT-MID)[ \t\n\x0B\f\r]*(\d+):(\d+)""".r
    val HSumE = new Regex("""(REG|REG1.0|OT1.0|OT1.25|OT1.35|OT1.5|OT1.6|HOL-1|HOL-2|HOL-NT|OT-MID)[ \t\n\x0B\f\r]*(\d+):(\d+)""")
    val tSum = "WRK   04:30, WRK1.2 4:20"
    val hSum = "OT1.35 13:15, OT1.6 01:00"
    // val hSum = "OT1.35 13:15"
    
//    line match {
//      case LogEntry(totalTime, viewTime, dbTime, responseCode, uri) => println(totalTime)
//      case _ => println("Nothing Found")
//    }
                          
    object I {
      def unapply(s: String): Option[Int] = 
        try { Some(s.toInt) }
        catch { case _: NumberFormatException => None }
    }
                          
    HSumEntry.findAllIn(hSum) foreach( sum => sum match {
      case HSumE(hType, I(hours), I(minutes)) => println("Hour Type: " + hType + ", Hours: " + hours + ", Minutes: " + minutes)
      case _ => println("Other")
    })
  }
}
