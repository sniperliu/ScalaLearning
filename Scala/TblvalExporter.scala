package com.citi.gta

import java.io.OutputStream
import java.net.URI

import scala.dbc._
import scala.dbc.Syntax._
import scala.dbc.syntax.Statement._

import db._
import util.ResUtil._

object TblvalExporter {

  def escapeChar(str: String): String = str match {
    case x if x != null => x.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
    case _ => "&nbsp;"
  }
  
  def main(args: Array[String]) {
//    val db = new Database(OracleVendor)
//
//    val rows = db.executeStatement (
//      select fields (("wbu_name" of characterVarying(32)) and ("wbu_email" of characterVarying(50))) 
//      from ("workbrain_user")
//    ) map { t => new {
//              val name = t("wbu_name").sqlString
//              val age = t("wbu_email").sqlString
//            }
//      }

//    for (val r <- rows;
//         val f <- r.fields) {
//      println(f.content.nativeValue) // or .sqlValue
//    }
      
//    for (usr <- rows) {
//      println(usr.name + " is " + usr.age)
//    }
//
//
//
//    db.close
/*    val sql = """
            select  wbltv_data_name,wbltv_data_desc,
            (select wbltv_data_name from workbrain.WB_LOCALZD_TBLVAL v1
            where v.wbltv_data_id = v1.wbltv_data_id
            and v1.client_id = 1
            and v1.wbl_id = 19
            and v1.wblt_id = v.wblt_id ) as chinese_name,
            (select wbltv_data_desc from workbrain.WB_LOCALZD_TBLVAL v2
            where v.wbltv_data_id = v2.wbltv_data_id
            and client_id = 1
            and v2.wbl_id = 19
            and v2.wblt_id = v.wblt_id) as chinese_desc
            from workbrain.WB_LOCALZD_TBLVAL v
            where client_id = 1
            and wbl_id = 1
        """
    val sql = """
select wbmldt_name, wbmldt_text from workbrain_msg_locale_data where regexp_like(wbmldt_text, 'GTA') and wbll_id in (19)
union 
select wbmldt_name, wbmldt_text from workbrain_msg_locale_data where regexp_like(wbmldt_text, 'Time & Attendance') and wbll_id in (19)
union 
select wbmldt_name, wbmldt_text from workbrain_msg_locale_data where regexp_like(wbmldt_text, 'Time and Attendance') and wbll_id in (19)
union 
select wbmldt_name, wbmldt_text from workbrain_msg_locale_data where regexp_like(wbmldt_text, 'Asia') and wbll_id in (19)
    """*/
    /*val sql = """
select wbmldt_name, wbmldt_text 
from workbrain_msg_locale_data
where wbmldt_id in (
45904, -184576, -184586, -184226, -184236, 44613, 
44533, -184186, -184196, 45478, 45475, 45473, 45468, 
45746, -184256, -200296, -200276, -200286, -200336, 
-200306, -200386, -200376, -200366, -200446, -200436, 
-200426, -200416, -200406, -200396, -200476, -200466, 
-200456, -200066, -200036, -200006, -200096, -200126, 
-200156, -200246, -200186, -200216, -182306, 45745, 45750, 
45751, -182403, -182266, -182346)
"""
    val sql = """ select wbmldt_name, wbmldt_text from workbrain_msg_locale_data where (lower(wbmldt_text) like '%portal%' or lower(wbmldt_text) like '%fhwone%') and wbll_id = 6 """;*/
    val sql = """
select org.wbmldt_name, org.wbmldt_text as c4u, bp.wbmldt_text as portal 
from workbrain_msg_locale_data org, workbrain_msg_locale_data_bp bp
where org.wbmldt_id = bp.wbmldt_id
  and org.wbmldt_name in (
  'GTA_TORF_ALERT_FOOTER', 'R3 CFJ END OF MONTH TIME ENTRY ALERT FOOTER', 'R3 CFJ END OF MONTH TIME ENTRY ALERT HEADER', 
'R3 CFJ SYS USAGE NOTIFY EMPLOYEE ALERT FOOTER', 'R3 CFJ SYS USAGE NOTIFY SUPERVISOR ALERT FOOTER', 
'R3 CFJ UNAUTHORISED EMP 1ST ALERT FOOTER', 'R3 CFJ UNAUTHORISED EMP 3RD ALERT FOOTER', 'R3 CFJ UNAUTHORISED SUP 1ST ALERT FOOTER', 
'R3 CFJ UNAUTHORISED SUP 3RD ALERT FOOTER', 'R3 JAPAN CBJ OT NOTIFY EMPLOYEE ALERT FOOTER', 
'R3 JAPAN CBJ OT NOTIFY SUPERVISOR ALERT FOOTER', 'R3 JAPAN CFJ OT NOTIFY EMPLOYEE ALERT FOOTER', 
'R3 JAPAN CFJ OT NOTIFY SUPERVISOR ALERT FOOTER', 'R3 JAPAN MODIFICATION TO TIMESHEET ALERT FOOTER', 
'R3 JAPAN NCL OT NOTIFY EMPLOYEE ALERT FOOTER', 'R3 JAPAN NCL OT NOTIFY SUP AND HR ALERT FOOTER', 
'R3 JAPAN UN-AU RECORD EMP 1ST ALERT FOOTER', 'R3 JAPAN UN-AU RECORD EMP 3RD ALERT FOOTER', 
'R3 JAPAN UN-AU RECORD NOTIFY SUP ALERT FOOTER', 'R3 JAPAN UN-AU RECORD SUP AND HR ALERT FOOTER', 
'TORF_MSG_URL'
)
"""
    val dest = ".\\result\\portaldecomm.html"
    var user1 = "workbrain"
    var pass1 = "disc123"; // "disc123"
    /*val TADEVA = new OracleVendor("corpdb305d.nam.nsroot.net", 3397, "CEGTDVA3") {
       val user = user1
       val pass = pass1
    }*/
    val CEGTDVA3 = new OracleVendor("corpdb305d.nam.nsroot.net", 3397, "CEGTDVA3") {
       val user = user1
       val pass = pass1
    }
    runQuery(CEGTDVA3.getConnection, sql, 
      rs => 
          using (new java.io.PrintWriter(dest, "UTF-8")) {
            target =>
              target.println("<html><meta http-equiv='Content-Type' content='text/html; charset=utf-8'><body>")
              target.println("<table border='1' cellPadding='0' cellSpacing='0'>")
              while(rs.next) {
                target.println("<tr><td>" + escapeChar(rs.getString(1)) + "</td><td>" + escapeChar(rs.getString(2)) + "</td><td>" + escapeChar(rs.getString(3)) + "</td></tr>")
              }
              target.println("</table></body></html>")
          }
    )
  }
}
