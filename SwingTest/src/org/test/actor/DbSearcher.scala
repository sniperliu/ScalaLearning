package org.test.actor

import java.net.URI

import scala.actors._
import Actor._
import scala.dbc._

import org.leo.util.ResUtil._

object DbSearcher {
  
  case object Start
  case class Query(q: String)
  case class Work(client: OutputChannel[Result], query: String)
  case class Result(r: String)
  
  class ClientActor(server: Actor, query: Query) extends Actor {
    def act() {
      loop {
        react {
          case Start => server ! query
          case Result(r) => println("Result: " + r); exit
        }
      }
    }
  }
  
  class WorkerActor(db: OracleVendor) extends Actor {
    def act() {
      loop {
        react {
          case Work(c, q) => 
            runQuery(db.getConnection, q, 
                     r => using(r) {
                            r => 
                              r.next
                              c ! Result(r.getString(1))
                              exit
                     })
        }
      }
    }
  }
  
  class ServerActor(number: Int) extends Actor {
    
    val TADEVA = new OracleVendor("corpdb305d.nam.nsroot.net", 3757, "CEGTDVA1") {
      val user = "workbrain"
      val pass = "dev2004apac"
    }
    
    def act() {
      loop {
        react {
          case Query(r) => 
            val worker = new WorkerActor(TADEVA)
            worker.start
            worker ! Work(sender, r)
        }
      }
    }
  }
  
  def main(args: Array[String]) {
    
    val server = new ServerActor(10)
    server.start
    
    val client1 = new ClientActor(server, Query("select emp_name from employee where emp_id in (select emp_id from workbrain_user where wbu_name = 'HL26184')"))
    val client2 = new ClientActor(server, Query("select emp_name from employee where emp_id in (select emp_id from workbrain_user where wbu_name = 'WORKBRAIN')"))
    client1.start
    client2.start
    
    client1 ! Start
    client2 ! Start
    
    println("End of Search")
  }

}

abstract class OracleVendor(url: String, port: Int, sid: String) extends Vendor {

  def retainedConnections = 5
  val nativeDriverClass = Class.forName("oracle.jdbc.OracleDriver")
  val urlProtocolString = "jdbc:oracle:oci:"
  
  // val uri = new URI(urlProtocolString + "@" + url + ":" + port + ":" + sid)
  val uri = new URI(urlProtocolString + "@" + sid)
}
