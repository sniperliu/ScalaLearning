package org.leo.scalacheck

import org.scalacheck._

object CheckString extends Properties("String") {

  import Prop._
  
  property("endsWith") = 
    forAll { (x: String, y: String) => { (x + y).endsWith(y) } }
  
//  val propMakeList = 
//    forAll { n: Int => n >= 0 ==> (List.make(n, "").length == n) }
  
//  val propTrivial = 
//    forAll { n: Int => (n == 0) ==> (n == 0) }
//  
//  def main(args: Array[String]) {
//    propMakeList.check
//  }
  
}
