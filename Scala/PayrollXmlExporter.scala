package com.citi.gta

import java.io.{InputStream, FileOutputStream}
import java.nio.ByteBuffer
import java.nio.channels.{ ReadableByteChannel, Channels, FileChannel }
import java.sql.DriverManager
import java.sql.{Statement, ResultSet, Clob}

import db._
import util.ResUtil._

object PayrollXmlExporter {
  
  def save(dir: String, filename: String, xml: Clob) { 
//    val SIZE = 1024
//    def read(in: Channel)(process: (Array[Byte], Int) => Unit): Unit = {
//      val size = in.read(byte_array)
//      if (size != -1) { process(byte_array, size); read(in)(process) }
//    }

    var fos: FileOutputStream = null
    var fc: FileChannel = null
    var clob: ReadableByteChannel = null
    try {      
      val name = filename.replace("/", "_")
      fos = new FileOutputStream(dir + name)
      fc = fos.getChannel()
      
      clob = Channels.newChannel(xml.getAsciiStream)
      
      val buffer = ByteBuffer.allocateDirect(512)
      while (clob.read(buffer) != -1) {
        buffer.flip
        fc.write(buffer)
        buffer.clear
      }
    } finally {   
      if (clob != null) clob.close
      if (fc != null) fc.close
      if (fos != null) fos.close
    }
  }
  
  def main(args: Array[String]) = {

    var dir = "./CTMS_PRD/"
    val user1 = args(0);
    val pass1 = args(1);
    val CEGTPRA = new OracleVendor("corpdb201p.nam.nsroot.net", 18602, "CEGTPRA") {
        val user = user1
        val pass = pass1
    }
    val CEGTUTA1 = new OracleVendor("corpdb301c.nam.nsroot.net", 18402, "CEGTUTA1") {
        val user = user1
        val pass = pass1
    }
    val CEGTDVA1 = new OracleVendor("corpdb305d.nam.nsroot.net", 3757, "CEGTDVA1") {
       val user = user1
       val pass = pass1
    }
    val CEGTDVA2 = new OracleVendor("corpdb305d.nam.nsroot.net", 2197, "CEGTDVA2") {
       val user = user1
       val pass = pass1
    }    
    val CEGTTNA1 = new OracleVendor("corpdb305d.nam.nsroot.net", 2297, "CEGTTNA1") {
       val user = user1
       val pass = pass1
    }
    val CEGTTNA3 = new OracleVendor("corpdb305d.nam.nsroot.net", 2878, "CEGTTNA3") {
       val user = user1
       val pass = pass1
    }
    // val sql = "select pet_name, pet_xml from payroll_export_tsk where pet_id > 10000"
    val sql = "select wbimap_name, wbimap_xml from wbint_mapping where wbimap_in_table = 'WBINT_IMPORT' and wbimap_xml is not null"
    runQuery(CEGTTNA3.getConnection, sql, 
      rs => 
        while (rs.next) {
          val name = rs.getString(1)
          val xml = rs.getClob(2)      
          save(dir, name + ".xml", xml)
        }
    )
//    val stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
//                                    ResultSet.CONCUR_READ_ONLY)
    
//    val db = new Database(OracleVendor); 
//    
//    try {
//      val rows = db.executeStatement {
//        select fields ("emp_name" of characterVarying(40)) from ("employee")
//      }
//      for (val r <- rows;
//           val f <- r.fields) {
//        Console.println(f.content.nativeValue) // or .sqlValue
//      }
//    } finally {
//      db.close;
//    }
  }
  
}
