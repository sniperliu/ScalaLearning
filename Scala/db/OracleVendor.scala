package com.citi.gta.db

import _root_.java.net.URI

import _root_.scala.dbc._

/**
 * 
 */
abstract class OracleVendor(url: String, port: Int, sid: String) extends Vendor {

  def retainedConnections = 5
  val nativeDriverClass = Class.forName("oracle.jdbc.OracleDriver")
  val urlProtocolString = "jdbc:oracle:thin:"
  
  val uri = new URI(urlProtocolString + "@" + url + ":" + port + ":" + sid)
  // val uri = new URI(urlProtocolString + "@" + sid)
}



object GTADBManger {
  
  case class GTADB(url: String, port: Int, sid: String, user: String, pass: String) 
  extends OracleVendor(url, port, sid)
  
  def TADEVA = GTADB("gtadb1d.nam.nsroot.net", 18802, "TADEVA", "workbrain", "dev2004apac")
//  val url = "gtadb1d.nam.nsroot.net"
//  val port = 18802
//  val sid = "TADEVA"
//  val user = "workbrain"
//  val pass = "dev2004apac"
}

//object TASITA extends OracleVendor {
//  val url = "gtadb1d.nam.nsroot.net"
//  val port = 28002
//  val sid = "TASITA"
//  val user = "workbrain"
//  val pass = "w0rkbra1n4"
//}
//
//object TATRNA extends OracleVendor {
//  val url = "gtadb1d.nam.nsroot.net"
//  val port = 18902
//  val sid = "TATRNA"
//  val user = "workbrain"
//  val pass = "northstar75"
//}
