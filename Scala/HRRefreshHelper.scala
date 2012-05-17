package com.citi.gta

// import _root_.java.util.Date

class Employee(val empName: String) {
  
  /*  
  val items = 
    "ovrStartDate" :: "ovrEndDate" :: "empName" :: "empLastName" :: "empFirstName" ::     
    "empDayStartTime" :: "shftpatName" :: "calcgrpName" :: "empBaseRate" :: 
    "paygrpName" :: "empHireDate" :: "empSeniorityDate" :: "empBirthDate" :: 
    "empTerminationDate" :: "empStatus" :: "empSIN" :: "empShftpatOffset" :: 
    "empFlag" :: "empVal1" :: "empVal2" :: "empVal3" :: "empVal4" :: 
    "empVal5" :: "empVal6" :: "empVal7" :: "empVal8" :: "empVal9" :: 
    "empVal10" :: "empVal11" :: "empVal12" :: "empVal13" :: "empVal14" :: 
    "empVal15" :: "empVal16" :: "empVal17" :: "empVal18" :: "empVal19" ::    
    "empVal20" :: "empDefMinutesRetailAvail" :: "empbdgBadgeNumber" :: 
    "empUDFData1" :: "empUDFData2" :: "empUDFData3" :: "empUDFData4" :: 
    "empUDFData5" :: "empUDFData6" :: "empUDFData7" :: "empUDFData8" ::     
    "empUDFData9" :: "empUDFData10" :: "empUDFData11" :: "empUDFData12" :: 
    "empUDFData13" :: "empUDFData14" :: "empUDFData15" :: "empUDFData16" :: 
    "empUDFData17" :: "empUDFData18" :: "empUDFData19" :: "empUDFData20" ::          
    "empDefLab1" :: "empDefLab2" :: "empDefLab3" :: "empDefLab4" :: 
    "empJobs" :: "empJobRateIndex" :: "unused" :: "emptName" :: 
    "entEmpPolicy" :: "wbuName" :: "wbuPassword" :: "wbuEmail" :: 
    "wbllID" :: "wbgID" :: "wbuPwdChangedDate" :: "embalDelta1" :: 
    "embalDelta2" :: "embalDelta3" :: "embalDelta4" :: "embalDelta5" :: 
    "embalDelta6" :: "embalDelta7" :: "embalDelta8" :: "embalDelta9" :: 
    "embalDelta10" :: Nil */
    
  val items = List("ovrStartDate", "ovrEndDate", "empName", "empLastName", "empFirstName",     
    "empDayStartTime", "shftpatName", "calcgrpName", "empBaseRate", 
    "paygrpName", "empHireDate", "empSeniorityDate", "empBirthDate", 
    "empTerminationDate", "empStatus", "empSIN", "empShftpatOffset", 
    "empFlag", "empVal1", "empVal2", "empVal3", "empVal4", 
    "empVal5", "empVal6", "empVal7", "empVal8", "empVal9", 
    "empVal10", "empVal11", "empVal12", "empVal13", "empVal14", 
    "empVal15", "empVal16", "empVal17", "empVal18", "empVal19",    
    "empVal20", "empDefMinutesRetailAvail", "empbdgBadgeNumber", 
    "empUDFData1", "empUDFData2", "empUDFData3", "empUDFData4", 
    "empUDFData5", "empUDFData6", "empUDFData7", "empUDFData8",     
    "empUDFData9", "empUDFData10", "empUDFData11", "empUDFData12", 
    "empUDFData13", "empUDFData14", "empUDFData15", "empUDFData16", 
    "empUDFData17", "empUDFData18", "empUDFData19", "empUDFData20",          
    "empDefLab1", "empDefLab2", "empDefLab3", "empDefLab4", 
    "empJobs", "empJobRateIndex", "unused", "emptName", 
    "entEmpPolicy", "wbuName", "wbuPassword", "wbuEmail", 
    "wbllID", "wbgID", "wbuPwdChangedDate", "embalDelta1", 
    "embalDelta2", "embalDelta3", "embalDelta4", "embalDelta5", 
    "embalDelta6", "embalDelta7", "embalDelta8", "embalDelta9", 
    "embalDelta10")

  var ovrStartDate: String = ""
  var ovrEndDate: String = ""
  var empLastName: String = ""
  var empFirstName: String = ""
  var empDayStartTime: String = ""
  var shftpatName: String = ""
  var calcgrpName: String = ""
  var empBaseRate: String = ""
  var paygrpName: String = ""
  var empHireDate: String = ""
  var empSeniorityDate: String = ""
  var empBirthDate: String = ""
  var empTerminationDate: String = ""
  var empStatus: String = "A"
  var empSIN: String = "123456"
  var empShftpatOffset: String = ""
  var empFlag: String = Array("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "").mkString("")
  var empVal1: String = ""
  var empVal2: String = ""
  var empVal3: String = ""
  var empVal4: String = ""
  var empVal5: String = ""
  var empVal6: String = ""
  var empVal7: String = ""
  var empVal8: String = ""
  var empVal9: String = ""
  var empVal10: String = ""
  var empVal11: String = ""
  var empVal12: String = ""
  var empVal13: String = ""
  var empVal14: String = ""
  var empVal15: String = ""
  var empVal16: String = ""
  var empVal17: String = ""
  var empVal18: String = ""
  var empVal19: String = ""
  var empVal20: String = ""
  var empDefMinutesRetailAvail: String = ""
  var empbdgBadgeNumber: String = ""
  var empUDFData1: String = ""
  var empUDFData2: String = ""
  var empUDFData3: String = ""
  var empUDFData4: String = ""
  var empUDFData5: String = ""
  var empUDFData6: String = ""
  var empUDFData7: String = ""
  var empUDFData8: String = ""
  var empUDFData9: String = ""
  var empUDFData10: String = ""
  var empUDFData11: String = ""
  var empUDFData12: String = ""
  var empUDFData13: String = ""
  var empUDFData14: String = ""
  var empUDFData15: String = ""
  var empUDFData16: String = ""
  var empUDFData17: String = ""
  var empUDFData18: String = ""
  var empUDFData19: String = ""
  var empUDFData20: String = ""
  var empDefLab1: String = ""
  var empDefLab2: String = ""
  var empDefLab3: String = ""
  var empDefLab4: String = ""
  var empJobs: String = ""
  var empJobRateIndex: String = ""
  var unused: String = ""
  var emptName: String = ""
  var entEmpPolicy: String = ""
  var wbuName: String = ""
  var wbuPassword: String = ""
  var wbuEmail: String = ""
  var wbllID: String = ""
  var wbgID: String = ""
  var wbuPwdChangedDate: String = ""
  var embalDelta1: String = ""
  var embalDelta2: String = ""
  var embalDelta3: String = ""
  var embalDelta4: String = ""
  var embalDelta5: String = ""
  var embalDelta6: String = ""
  var embalDelta7: String = ""
  var embalDelta8: String = ""
  var embalDelta9: String = ""
  var embalDelta10: String = ""
  
  def toCSV = {
    List(ovrStartDate, ovrEndDate, empName, empLastName, empFirstName, 
    empDayStartTime, shftpatName, calcgrpName, empBaseRate, 
    paygrpName, empHireDate, empSeniorityDate, empBirthDate, 
    empTerminationDate, empStatus, empSIN, empShftpatOffset, 
    empFlag, empVal1, empVal2, empVal3, empVal4, 
    empVal5, empVal6, empVal7, empVal8, empVal9, 
    empVal10, empVal11, empVal12, empVal13, empVal14, 
    empVal15, empVal16, empVal17, empVal18, empVal19,    
    empVal20, empDefMinutesRetailAvail, empbdgBadgeNumber, 
    empUDFData1, empUDFData2, empUDFData3, empUDFData4, 
    empUDFData5, empUDFData6, empUDFData7, empUDFData8,     
    empUDFData9, empUDFData10, empUDFData11, empUDFData12, 
    empUDFData13, empUDFData14, empUDFData15, empUDFData16, 
    empUDFData17, empUDFData18, empUDFData19, empUDFData20,          
    empDefLab1, empDefLab2, empDefLab3, empDefLab4, 
    empJobs, empJobRateIndex, unused, emptName, 
    entEmpPolicy, wbuName, wbuPassword, wbuEmail, 
    wbllID, wbgID, wbuPwdChangedDate, embalDelta1, 
    embalDelta2, embalDelta3, embalDelta4, embalDelta5, 
    embalDelta6, embalDelta7, embalDelta8, embalDelta9, 
    embalDelta10).mkString("\"", "\",\"", "\"")
  }
}

object HRRefresh {
  def main(args: Array[String]) {
    /*
    val items: List[String] = 
      "empVal15" :: "empVal16" :: "empVal17" :: "empVal18" :: "empVal19" ::   
      "empVal20" :: "empDefMinutesRetailAvail" :: "empbdgBadgeNumber" :: 
        "empUDFData1" :: "empUDFData2" :: "empUDFData3" :: "empUDFData4" :: 
        "empUDFData5" :: "empUDFData6" :: "empUDFData7" :: "empUDFData8" :: 
        "empUDFData9" :: "empUDFData10" :: "empUDFData11" :: "empUDFData12" :: 
        "empUDFData13" :: "empUDFData14" :: "empUDFData15" :: "empUDFData16" :: 
        "empUDFData17" :: "empUDFData18" :: "empUDFData19" :: "empUDFData20" :: 
        "empDefLab1" :: "empDefLab2" :: "empDefLab3" :: "empDefLab4" :: 
        "empJobs" :: "empJobRateIndex" :: "unused" :: "emptName" :: 
        "entEmpPolicy" :: "wbuName" :: "wbuPassword" :: "wbuEmail" :: 
        "wbllID" :: "wbgID" :: "wbuPwdChangedDate" :: "embalDelta1" :: 
        "embalDelta2" :: "embalDelta3" :: "embalDelta4" :: "embalDelta5" :: 
        "embalDelta6" :: "embalDelta7" :: "embalDelta8" :: "embalDelta9" :: 
        "embalDelta10" :: Nil
        
      val items: List[Int] = 
            1 :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: 8 :: 9 :: 
            10 :: 11 :: 12 :: 13 :: 14 :: 15 :: 16 :: 
            17 :: 18 :: 19 :: 20 :: 21 :: 22 :: 23 :: 
            24 :: 25 :: 26 :: 27 :: 28 :: 29 :: 30 :: 
            31 :: 32 :: 33 :: 34 :: 35 :: 36 :: 37 :: Nil
            // 38 :: 39 :: 40 :: 41 :: 42 :: 43 :: 44 :: 
            // 45 :: 46 :: 47 :: 48 :: 49 :: 50 :: 51 :: Nil*/
      
      val emp1 = new Employee("S000214884")
      emp1.ovrStartDate = "01/01/2010"
      emp1.empLastName = ""
      emp1.empFirstName = ""
      emp1.shftpatName = ""
      emp1.calcgrpName = ""
      emp1.empBaseRate = ""
      emp1.paygrpName = "TH000~~~~~~~~~~"
      emp1.empHireDate = ""
      emp1.empSeniorityDate = ""
      emp1.empBirthDate = ""
      emp1.empTerminationDate = ""
      emp1.empStatus = ""
      emp1.empFlag = ""
      emp1.empVal1 = ""
      emp1.empVal3 = ""
      emp1.empVal5 = ""
      emp1.empUDFData1 = ""
      emp1.empUDFData2 = ""
      emp1.empUDFData3 = ""
      emp1.empUDFData4 = ""
      emp1.empUDFData5 = ""
      emp1.empUDFData6 = ""
      emp1.empUDFData7 = ""
      emp1.empUDFData8 = ""
      emp1.empDefLab1 = ""
      emp1.emptName = ""
      emp1.entEmpPolicy = ""
      emp1.wbuName = ""
      emp1.wbuPassword = ""
      emp1.wbgID = ""
      
//      val emp2 = new Employee("1000260901")
//      emp2.ovrStartDate = "01/01/2007"
//      emp2.empLastName = "Price"
//      emp2.empFirstName = "Nancy"
//      emp2.shftpatName = "NOT ASSIGNED"
//      emp2.calcgrpName = "NULL"
//      emp2.empBaseRate = "0"
//      emp2.paygrpName = "0"
//      emp2.empHireDate = "01/01/2005"
//      emp2.empSeniorityDate = "01/01/2005"
//      emp2.empBirthDate = "06/12/1970"
//      emp2.empTerminationDate = "01/01/3000"
//      emp2.empStatus = "A"
//      emp2.empFlag = "Y R                 "
//      emp2.empVal1 = "12/16/2006"
//      emp2.empVal3 = "NJ"
//      emp2.empVal5 = "Warren"
//      emp2.empUDFData1 = "GENDER~F"
//      emp2.empUDFData2 = "JOB CODE~AP6580"
//      emp2.empUDFData3 = "JOB TITLE~Senior Supervisor"
//      emp2.empUDFData4 = "CONTRACT START DATE~"
//      emp2.empUDFData5 = "CONTRACT END DATE~"
//      emp2.empUDFData6 = "CHILD BIRTH DATE~"
//      emp2.empUDFData7 = "PROMOTION DATE~"
//      emp2.empUDFData8 = "AC NAME~Wdndfkl,Nxplnr"
//      emp2.empDefLab1 = "100~NULL DOCKETA~REG~Job~WRK~0~JPCF0HQ00C0E1"
//      emp2.emptName = "WORKBRAIN ROOT"
//      emp2.entEmpPolicy = "NONE"
//      emp2.wbuName = "NP60901"
//      emp2.wbuPassword = "39"
//      emp2.wbgID = "SUPERVISOR_XCOUNT"
      
      val emps = emp1 :: Nil
      
      import util.ResUtil._
      val dest = ".\\result\\tha.csv"
      val target = new java.io.PrintWriter(new java.io.FileWriter(dest))
      using(target) {
        src => 
          src.println("EMPLOYEE_EXPORT_FILE_HEADER")
          emps.foreach(emp => src.println(emp.toCSV))
          src.println("EMPLOYEE_EXPORT_FILE_TRAILER," + emps.size)
      }
  }
}
