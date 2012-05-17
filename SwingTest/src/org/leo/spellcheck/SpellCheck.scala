package org.leo.spellcheck

import scala.collection.mutable._
import scala.io._

object SpellCheck {
  
  val factor = 1
  val NWORDS = train(words(Source.fromFile("data/big.txt")))
  val alphabet = "abcdefghijklmnopqrstuvwxyz";
  
  def words(src: Source): List[String] = {
    var ws = List[String]()
    val pattern = """([a-z]+)""".r
    src.getLines.foreach(l => pattern.findAllIn(l).foreach(m => ws = m :: ws))
    ws
  }
  
  def train(words: List[String]): Map[String, Int] = {
    var dict = Map[String, Int]()
    for (w <- words) {
      dict.get(w) match {
        case Some(_) => dict(w) = dict(w) + factor
        case None => dict += w -> factor
      }
    }
    dict
  }
  
  def edit1(word: String): Set[String] = {
    Set[String]()
  }

  def correct(word: String): String = {
    ""
  }
  
  def main(args: Array[String]) {
    for { (key, item) <- NWORDS } {
      println("Key: " + key + ", count: " + item)
    }
  }
  
}
