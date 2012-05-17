package com.citi.gta.util

import java.sql.{Connection, ResultSet}

object ResUtil {
  
  trait Disposable[S] {
    def dispose
  }
  
  implicit def close2disposable[S]( what: S { def close() } ): Disposable[S] = 
    new Disposable[S] {
      def dispose() {
        try {
          what.close
        } catch {
          case t => ()
        }
      }
    } 
  
  implicit def dispose2disposable[S](what: S { def dispose() }): Disposable[S] = 
    new Disposable[S] {
      def dispose() {
        what.dispose
      }
    }
  
  def using[S <% Disposable[S], T](resource: S)(f: S => T ): T = {
    try {
      f( resource )
    } finally {
      resource.dispose
    }
  }
  
  def runQuery[T](conn: Connection, sql: String, ops: (ResultSet) => T) {
    using(conn) {
      con => 
        using(con.createStatement) {
          stmt => 
            using(stmt.executeQuery(sql)) {
              rs => ops(rs)
            }
        }
    }
  }
  
  import java.io._
  import java.nio._
  import java.nio.channels._
  def copyFileToDir(src: File, target: String) {
    val srcFile = new FileInputStream(src)
    val targetFile = new FileOutputStream(target + File.separator + src.getName)    
    using(srcFile) {
      srcF =>
        using(targetFile) {
          targetF => 
            val buffer = ByteBuffer.allocateDirect(512)
            val finc = srcF.getChannel
            val foutc = targetF.getChannel
            while (finc.read(buffer) != -1) {
              buffer.flip
              foutc.write(buffer)
              buffer.clear
            }
        }
    }
  }
  
}
