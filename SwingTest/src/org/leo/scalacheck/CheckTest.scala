package org.leo.scalacheck

import org.scalacheck.Prop._
import org.scalacheck.Gen
import org.scalacheck.Test

object CheckTest {
  
  def myMagicFunction(n: Int, m: Int) = m + n

  val complexProp = forAll { (n: Int, m: Int) => 
    val res = myMagicFunction(n, m)
    (res >= m) :| "result > #1" && 
    (res >= n) :| "result > #2" &&
    (res < m + n) :| "result not sum"
  }
  
 
  def ordered(l: List[Int]) = l == l.sort(_ > _)
  val myProp = forAll { l: List[Int] => 
      classify(ordered(l), "ordered") { 
        classify(l.length > 5, "large", "small") { 
          l.reverse.reverse == l 
        } 
      } 
    }
       
   def main(args: Array[String]) {
     val myGen = for {
       n <- Gen.choose(10, 20)
       m <- Gen.choose(2 * n, 500)
     } yield (n, m)
     
     //val vowel = Gen.oneOf('A', 'E', 'I', 'O', 'U', 'Y')
     //val vowel: Gen[Char] = 'A' | 'E' | 'I' | 'O' | 'U' | 'Y'
     val vowel = Gen.frequency(
       (3, 'A'),
       (4, 'E'),
       (2, 'I'),
       (3, 'O'),
       (1, 'U'),
       (1, 'Y')
     )
     
     Test.check(myProp)
   }
}
