package org.leo

import java.net.URL

import scala.io.Source

object SiteReader {
  
  def main(args: Array[String]) {
    val input = new URL("http://www.ytzw.net/files/article/html/22/22644/99979.html").openStream
    Source.fromInputStream(input).getLines.foreach(println _)
  }

}
