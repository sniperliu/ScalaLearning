package com.citi.gta

import java.io._

import scala.io._

case class MigCmd(home: String, cmd: String, dbDef: String, 
                  passwd: String, clientName: String) {
  
  def getCmd(folder: String, xmlFile: String): String = {
    cmd + " -migrate " + 
    folder + File.separator + xmlFile + 
    " " + dbDef + " " + passwd + " " + clientName
  }
  
}

object MigrationProcess {
  
  val home = "C:/Users/hl26184/LiuHao/Workbrain/Tools/mt41Jan12_FP52"
  val cmd = "migration_tool.bat"
  val dbDef = "wb_cegtdva1"
  val passwd = "dev2004apac"
  val client = "DEFAULT"
  
  def main(args: Array[String]) {
    val mig = MigCmd(home, cmd, dbDef, passwd, client)
    val folder = "C:/Users/hl26184/Desktop/NewMLReport"
    
    val rt = new ProcessBuilder(mig.getCmd(folder, "CR3370529_BOTVR10_20100709.xml"))
    rt.directory(new File(home))
    val proc = rt.start
    val c = proc.waitFor
    if (c != 0) {
      val err = Source.fromInputStream(proc.getErrorStream)
      err.getLines.foreach(println _)
      val std = Source.fromInputStream(proc.getInputStream)
      std.getLines.foreach(println _)
      println("Task failed.")
    } else {
      val std = Source.fromInputStream(proc.getInputStream)
      std.getLines.foreach(println _)
    }
    
  }

}
