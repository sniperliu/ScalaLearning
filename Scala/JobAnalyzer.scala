package com.citi.gta

import java.text._
import java.util.{TimeZone, Calendar, GregorianCalendar, Date}

import scala.collection.immutable._
import scala.io.Source
import scala.util.matching._
import scala.xml._

case class Job(id: String, desc: String, active: String, frequency: String, time: String, timeZone: Int) {
  override def toString() = {
    val tz = JobAnalyzer.timezone(timeZone)
  
    id + "," + desc + "," + active + "," + frequency + "," + time + "," + 
    tz + "," + (if (time.trim != "") JobAnalyzer.convertTimeZone(time, tz, "America/New_York") else "")
  }
}

case class JobLog(id: String, time: String, status: String)

/**
select jstsk_id, jstsk_desc, to_char(jstsk_schedule) schedule
from jobskd_task
where jstsk_active = 'Y' and jstsk_deleted = 'N'
  and jstsk_ref_id is null
  
  
select jstsk_id, to_char(jslog_time, 'mm/dd/yyyy hh24:mi:ss') t, jslog_status
from jobskd_log
where jstsk_id in (
  select jstsk_id
  from jobskd_task
  where jstsk_active = 'Y' and jstsk_deleted = 'N'
    and jstsk_ref_id is null)
  and jslog_time > to_date('20110101', 'yyyymmdd')
order by jstsk_id, jslog_time, jslog_status desc
*/
object JobAnalyzer {
  
  val timezone = HashMap[Int, String]((0, "America/New_York"), 
      (30, "Asia/Jayapura"), (62, "Australia/West"), 
      (10001, "Asia/Singapore"), (68, "Australia/Brisbane"),
      (13, "Atlantic/Canary"), (15, "ECT")); 
  
  def convertTimeZone(time: String, srcZone: String, targetZone: String): String = {
    val df = new SimpleDateFormat("HH:mm:ss")
    val srctz = TimeZone.getTimeZone(srcZone)
    val targettz = TimeZone.getTimeZone(targetZone)

    val cal = new GregorianCalendar(srctz)
    val cals = new GregorianCalendar(srctz)
    df.setCalendar(Calendar.getInstance)
    df.setTimeZone(srctz)
    cals.setTime(df.parse(time))
    cals.set(Calendar.YEAR, cal.get(Calendar.YEAR))
    cals.set(Calendar.MONTH, cal.get(Calendar.MONTH))
    cals.set(Calendar.DATE, cal.get(Calendar.DATE))

    val calt = new GregorianCalendar(targettz)
    calt.setTimeInMillis(cals.getTimeInMillis)

    df.setTimeZone(targettz)
    df.format(calt.getTime)
  }
  
  // If use the unbox object, then how to filter
//  object Schedule {
//    def unapply(s: String): Option[String] = {
//      val xml = XML.loadString(s)
//      Some((xml \ "time").text)
//    }
//  }
  
  def retrieveTime(s: String) = {
    val xml = XML.loadString(s)    
    ((xml \\ "frequency").head.child.head.label, 
     (xml \\ "time").text, (xml \\ "timezoneId").text)
  }
  
  def escapeQuote(s: String) = {
    s.replace("\"\"", "\"")
  }
  
  def jobConverter(job: String): Job = {
    // val JobDesc = new Regex("(.*),(.*),(.*),(?:\")?(.*?)(?:\")*,")
    val JobDesc = new Regex("\"(.*)\",\"(.*)\",\"(.*)\"")
    job.trim match {
      case JobDesc(id, desc, schedule) if (schedule != null && !schedule.trim.isEmpty) => {
        val time = retrieveTime(escapeQuote(schedule))
        Job(id, desc, "Y", time._1, time._2, Integer.parseInt(if (time._3 != "") time._3 else "10001" ))
      }
      case _ => Job("0", "", "", "", "", 0)
    }
  }
  
  def getDiffInSec(start: String, end: String): Long = {
    val df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
    
    val dStart = df.parse(start)
    val dEnd = df.parse(end)
    
    (dEnd.getTime - dStart.getTime) / 1000    
  }
  
  def jobLogConverter(log: String): JobLog = {
    val JobLogRegex = new Regex("(.*),(.*),(.*),")
    log.trim match {
      case JobLogRegex(id, time, status) => 
        JobLog(id, time, status)
      case _ => throw new Exception()
    }
  }
  
  def logSummary(jobLogs: List[JobLog], 
      result: List[(String, List[Long])]): List[(String, List[Long])] = {
    if (jobLogs.isEmpty) {
      result
    } else {
      val log1 = jobLogs.head
      logSummary(jobLogs.dropWhile(x => x.id == log1.id), 
          (log1.id, taskSummary(jobLogs.takeWhile(x => x.id == log1.id), List.empty[Long])) :: result)
    }
  }
  
  def taskSummary(taskLogs: List[JobLog], result: List[Long]): List[Long] = {
    taskLogs match {
      // Regarding HR Import task, any records error is taken as FAIL, but
      // Actually the task is finished
      case s :: e :: ls if (s.status == "STARTED" && e.status != "STARTED") => 
        val t = getDiffInSec(s.time, e.time) 
        if (t > 0) taskSummary(ls, t :: result) else taskSummary(ls, result)
      case s :: e :: ls if (e.status == "STARTED") => 
        taskSummary(e :: ls, result)
      case s :: e :: ls => 
        taskSummary(ls, result)
      case _ => result
    }
  }
  
  def sum(l: List[Long]) = l.foldLeft(0l)((x: Long, y: Long) => x + y)
  def max(l: List[Long]) = l.foldLeft(l.head)((x: Long, y: Long) => if (x > y) x else y)
  def min(l: List[Long]) = l.foldLeft(l.head)((x: Long, y: Long) => if (x < y) x else y)
  
  def mergeJobTime(job: Job, tMap: Map[String, List[Long]]) = {
    tMap.get(job.id) match {
      case Some(x) if (!x.isEmpty) => 
        job.toString + "," + max(x) + "," + min(x) + "," + sum(x) / x.size
      case _ =>
        job.toString + ",NA,NA,NA"
    }
  }
  
  def main(args: Array[String]) = {
    val ftasks = "C:/Users/hl26184/workspace/GTAHelper/data/Tasks_CTMS.csv"
    val jobs = Source.fromFile(ftasks).getLines.map(jobConverter)
    
    //val ftimeline = "C:/Users/hl26184/workspace/GTAHelper/data/Time_APAC.csv"
    //val tlog = Source.fromFile(ftimeline).getLines.map(jobLogConverter).toList    
    /*logSummary(tlog, Nil).foreach(s => 
      if (!s._2.isEmpty) println(s._1 + ", " + max(s._2) + ", " + min(s._2) + ", " + sum(s._2) / s._2.size)
      else println(s._1 + ""))*/
    //val tmap = logSummary(tlog, Nil).toMap
    
    //jobs.map(job => mergeJobTime(job, tmap)).foreach(println)
    jobs.foreach(println)
  }
}
