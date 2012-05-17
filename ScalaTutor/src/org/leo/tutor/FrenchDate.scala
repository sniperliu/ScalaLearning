package org.leo.tutor

import _root_.java.util.{Date, Locale}
import java.text.DateFormat._

object FrenchDate {
  def main(args : Array[String]) {
    val now = new Date
    val df = getDateInstance(LONG, Locale.US)
    println(df format now)
    
    val x = 2
    println(1 + 2 * 3 / x)
  }
}
