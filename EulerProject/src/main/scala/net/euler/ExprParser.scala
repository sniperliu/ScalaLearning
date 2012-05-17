package net.euler

sealed abstract class Expr {
  def eval(): Double
}

case class EConst(value: Double) extends Expr {
  def eval(): Double = value
}

case class EAdd(left: Expr, right: Expr) extends Expr {
  def eval(): Double = left.eval + right.eval
}

import scala.util.parsing.combinator.syntactical._

object ExprParser extends StandardTokenParsers {
  lexical.delimiters ++= List("+")
  
  def value = numericLit ^^ { s => EConst(s.toDouble) }
  
  def sum = value ~ "+" ~ value ^^ { case left ~ "+" ~ right => EAdd(left, right) }
  
  def expr = (sum | value)
  
  def parse(s: String) = {
    val tokens = new lexical.Scanner(s)
    phrase(expr)(tokens)
  }
  
  def apply(s: String): Expr = {
    parse(s) match {
      case Success(tree, _) => tree
      case e: NoSuccess => throw new IllegalArgumentException("Bad syntax: " + s)
    }
  }
  
  //Simplify testing
  def test(exprstr: String) = {
    parse(exprstr) match {
      case Success(tree, _) =>
        println("Tree: "+tree)
        val v = tree.eval()
        println("Eval: "+v)
      case e: NoSuccess => Console.err.println(e)
    }
  }

    //A main method for testing
  def main(args: Array[String]) = test("12 + 34")
}
