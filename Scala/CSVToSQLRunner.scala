package com.citi.gta

import java.io.{InputStream, FileOutputStream}
import java.nio.ByteBuffer
import java.nio.channels.{ ReadableByteChannel, Channels, FileChannel }
import java.sql.DriverManager
import java.sql.{Statement, ResultSet, Clob}

import scala.io._

import db._
import util.ResUtil._

object CSVToSQLRunner {
  
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
  
  def convertToSQL(line: String): String = {
    val cols = line.split(",")
    
//      "Insert into MAINTENANCE_FORM_GRP " + 
//      "(MFG_ID, MFRM_ID, WBG_ID, CLIENT_ID, WBP_ID) " + 
//      "select seq_mfg_id.nextval, mfrm_id, " + 
//      "(select wbg_id from workbrain_group where wbg_name = '" + cols(1) + "')," +
//      "1, " + cols(3) +
//      " from maintenance_form " +
//      " where mfrm_name in (' " + cols(2) + "')" 
    
        "update jobskd_task set jstsk_desc = '" + cols(5) + "' where jstsk_desc = '" + cols(4) + "';"
    
  }
 
  def main(args: Array[String]) = {

    var dir = "./CTMS_SIT/"
    val user1 = "workbrain"
    val pass1 = "may23orig"
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
    // val frmAcc = "C:\\Users\\hl26184\\Desktop\\STE\\Field_and_Form_Access\\PRD_Form_Access.csv"
    val fl = "C:\\Users\\hl26184\\workspace\\GTAHelper\\data\\jobAutosys.csv";
//    val sql =       "Insert into MAINTENANCE_FORM_GRP " + 
//      "(MFG_ID, MFRM_ID, WBG_ID, CLIENT_ID, WBP_ID) " + 
//      "select seq_mfg_id.nextval, mfrm_id, " + 
//      "(select wbg_id from workbrain_group where wbg_name = ?)," +
//      "1, ?" + 
//      " from maintenance_form " +
//      " where mfrm_name = ?" 
    val sql = "select * from change_history where chnghist_change_date >= ?"
//    val conn = CEGTTNA3.getConnection
//    val pStmt = conn.prepareStatement(sql)
//    Source.fromFile(frmAcc).getLines.foreach((line: String) => {
//      val cols = line.split(",")
//      pStmt.setString(1, cols(1))
//      // pStmt.setInt(2, Integer.valueOf(cols(3)))
//      // pStmt.setString(3, cols(2))
//      pStmt.execute()
//    })
//    pStmt.close()
//    conn.close()
      
      Source.fromFile(fl).getLines.map(convertToSQL).foreach(println)
  }
  
}
