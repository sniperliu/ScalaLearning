package net.liftweb.util

import org.scalacheck.Prop._

import org.specs._

object StringSpecification extends Specification("String") with ScalaCheck {
  "String should follow below" should {
    "startswith" in {
      forAll((a: String, b: String) => (a + b).startsWith(a)) must pass
    }
    
    "endsWith" in {
      forAll((a: String, b: String) => (a + b).endsWith(b)) must pass
    }
  }
}

import org.specs.runner.{JUnit4}

class StringSpecsTest extends JUnit4(StringSpecification)
