package com.citi.gta

import scala.collection.immutable._
import scala.io.Source
import scala.util.matching._
import scala.xml._

trait SQLStatement;

case class SelectTable(table: String, condition: String) extends SQLStatement {  
  override def toString(): String = {
    "create table " + table + "_copy as select * from employee where " + condition + ";";
  }
}

case class Truncate(table: String) extends SQLStatement {  
  override def toString(): String = {
    "truncate table " + table + ";";
  }
}

case class SelectInsert(table: String) extends SQLStatement {  
  override def toString(): String = {
    "insert into  " + table + " select * from " + table + "_copy" + ";";
  }
}

object SQLConverter {
  
  def convertSql(sql: String, isInsertBack: Boolean): List[SQLStatement] = {
    val SQLDesc = new Regex("DELETE FROM\\s*(\\w*)\\s*WHERE\\s*(.*);")
    sql.trim match {
      case SQLDesc(table, condition) if !isInsertBack => {
        // "Table: " + table + ", Condition: " + condition
        SelectTable(table, condition) :: Truncate(table) :: Nil
      }
      case SQLDesc(table, condition) if isInsertBack => {
        // "Table: " + table + ", Condition: " + condition
        SelectInsert(table) :: Nil
      }
      case _ => Nil
    }
  }

  def main(args: Array[String]): Unit = { 
    val ftasks = "C:/Users/hl26184/workspace/GTAHelper/data/del_employee_plSql.sql"
    val sql1 = Source.fromFile(ftasks).getLines.toList
    sql1.flatMap(x => convertSql(x, false)).foreach(println)
    sql1.reverse.flatMap(x => convertSql(x, true)).foreach(println)
  }
}
