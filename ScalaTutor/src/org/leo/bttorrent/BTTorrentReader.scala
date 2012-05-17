package org.leo.bttorrent

import scala.io._

object BTTorrentReader {

  def main(args: Array[String]): Unit = { 
      
      val bt = Source.fromFile("eclipse-java-helios-SR1-win32.zip.torrent")
      bt.getLines().foreach(println _)
  }

}
