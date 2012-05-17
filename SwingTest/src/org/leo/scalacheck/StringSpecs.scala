package org.leo.scalacheck

import org.specs.SpecificationWithJUnit
import org.specs.ScalaCheck

class StringSpecs extends SpecificationWithJUnit("String") with ScalaCheck {
  
  "String" should {
    
    "provide startsWith" in {
      val a = "Hello "
      val b = "World"
      a + b must equalTo("Hello World")
    }
    "provide startsWith" verifies { (a: String, b: String) => (a + b).startsWith(a) }
  }
  
}
