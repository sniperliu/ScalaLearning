package org.leo.eval

object App {
  
  trait Expr {
    type T
    def eval: T
  }
  
  trait BoolExpr extends Expr {
    type T = Boolean
  }
  
  case class And(a: BoolExpr, b: BoolExpr) extends BoolExpr {
    def eval = a.eval && b.eval
  }
  
  case class Or(a: BoolExpr, b: BoolExpr) extends BoolExpr {
    def eval = a.eval || b.eval
  }
  
  case class isA(str: String) extends BoolExpr {
    def eval = "A".equals(str)
  }
  
  def main(args: Array[String]) {
    println(And(isA("ABC"), isA("A")).eval)
    println(Or(isA("ABC"), isA("A")).eval)
  }

}
