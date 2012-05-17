package com.citi.gta

import java.io.{InputStream, FileOutputStream}
import java.nio.ByteBuffer
import java.nio.channels.{ ReadableByteChannel, Channels, FileChannel }
import java.sql.DriverManager
import java.sql.{Statement, ResultSet, Clob}

import db._
import util.ResUtil._

object CalcgrpXmlExporter {
  
    def save(filename: String, xml: Clob) { 

    var fos: FileOutputStream = null
    var fc: FileChannel = null
    var clob: ReadableByteChannel = null
    try {      
      val name = filename.replace("/", "_")
      fos = new FileOutputStream("./calcgrp_tadeva/" + name)
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
    var user1 = "workbrain"
    var pass1 = "dev2004apac"
//    val TADEVA = new OracleVendor("corpdb305d.nam.nsroot.net", 3757, "CEGTDVA1") {
//       val user = user1
//       val pass = pass1
//    }
    val TADEVA = new OracleVendor("gtadb1d.nam.nsroot.net", 18802, "TADEVA") {
       val user = user1
       val pass = pass1
    }    
    val sql = """
      select calcgrp_name, cgv_xml 
      from calc_group_version v, calc_group grp
      where v.calcgrp_id = grp.calcgrp_id
        and cgv_id not in(10492, 10493)
        and calcgrp_name not like '%TEST%'
        and cgv_xml is not null
    """
    runQuery(TADEVA.getConnection, sql, 
      rs => 
        while (rs.next) {
          val name = rs.getString(1)
          val xml = rs.getClob(2)      
          save(name + ".xml", xml)
        }
    )
  }
  
}
