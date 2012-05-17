package net.liftweb.util

import org.scalacheck._
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen._
import org.specs._
import org.specs.runner.JUnit4
import org.specs.matcher.ScalaCheckParameters

class BoxSpecTest extends JUnit4(BoxSpecs, StringSpecification)

object BoxSpecs extends Specification("Box in Lift") with ScalaCheck with BoxGen {
  "A Box equals method" should {
    "return true when comparing two identical Box messages" in {
      val equality = (b1: Box[Int], b2: Box[Int]) => 
        (b1, b2) match {
          case (Empty, Empty) => b1 == b2
          case (Full(x), Full(y)) => (b1 == b2) == (x == y)
          case (Failure(m1, e1, l1), Failure(m2, e2, l2)) =>
            (b1 == b2) == ((m1, e1, l1) == (m2, e2, l2))
          case _ => b1 != b2
        }
      Prop.forAll(equality) must pass
    }    
  }
}

trait BoxGen {
  implicit def arbBox[T](implicit a: Arbitrary[T]): Arbitrary[Box[T]] =          
    Arbitrary {
      def genFailureBox: Gen[Failure] = for {
        msgLen <- choose(0, 4)
        msg <- vectorOf(msgLen, alphaChar)
        exception <- arbitrary[Box[Throwable]]
        chainLen <- choose(1, 5)
        chain <- frequency(
                   (1, vectorOf(chainLen, genFailureBox)),
                   (3, value(Nil)))
      } yield Failure(msg.mkString, exception, Box(chain))
      
      frequency (
        (3, value(Empty)),
        (3, arbitrary[T].map(Full[T])),
        (1, genFailureBox)
      )
  }
}
