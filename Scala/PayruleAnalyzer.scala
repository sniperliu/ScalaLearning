package com.citi.gta

import java.io.InputStream
import java.sql.{Statement, ResultSet, Clob}

import scala.xml._

import db._
import util.ResUtil._

object PayruleAnalyzer {
  
  def analyze(name: String, xml: InputStream) {
    val rule = XML.load(xml)
    for (conditionSet <- rule \\ "conditionset") {
      if ((conditionSet \ "@description").text == "Weekly Adjustment for Part Time Accruals") {
        println("CalculationGroup: " + name + ": " + 
                getValues(conditionSet, "Worked hour type list") + " - " + 
                getValues(conditionSet, "Hour type list inclusive"))
      }
    }
  }
  
  def getValues(seq: NodeSeq, name: String): String = {
    val v = 
      for {param <- seq \ "parameter"
           if ((param \ "name").text == name)
      } yield {
        (param \ "value").text
      }
    v.first
    // ((seq \ "parameter").filter( (param: Node) => { (param \ "name").text == name } ).first \ "value").text
  }
  
  def main(args: Array[String]) {
    var user1 = "workbrain"
    var pass1 = "dev2004apac"
    val TADEVA = new OracleVendor("gtadb1d.nam.nsroot.net", 18802, "TADEVA") {
       val user = user1
       val pass = pass1
    }
    val sql = """
        select cg.calcgrp_name, cgv.cgv_xml
        from calc_group cg, calc_group_version cgv
        where cg.calcgrp_id = cgv.calcgrp_id
          and cg.calcgrp_name like 'AUS%'
    """
    runQuery(TADEVA.getConnection, sql, 
      rs => 
        while (rs.next) {
          val name = rs.getString(1)
          val xml = rs.getClob(2)      
          analyze(name, xml.getAsciiStream)
        }
    )
  }
  
}
