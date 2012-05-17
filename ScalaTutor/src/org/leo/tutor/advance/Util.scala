package org.leo.tutor.advance

/** Illustrate the use of custom 'apply/update' methods. */
object Util {

  def using[T <: { def close() }](resource: T)(doing: T => Unit) = {
    try {
      doing(resource)
    } finally {
      if (resource != null) resource.close
    }
  }

  def main(args: Array[String]) = {
    import java.io._
    val file = "C:\\Users\\hl26184\\Desktop\\user_access_TWN.csv"
    using(new BufferedReader(new FileReader(file))) {
      res =>
        var line = res.readLine
        while (line != null) {
          println(line)
          line = res.readLine
        }
    }
  }
}
