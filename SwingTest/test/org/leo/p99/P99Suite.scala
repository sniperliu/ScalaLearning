package org.leo.p99

import org.junit.Before
import org.junit.Test

import org.scalatest.junit.JUnitSuite

class P99Suite extends JUnitSuite {
  
  import ListUtil._
  
  val ls: List[Int] = List(1, 1, 2, 3, 5, 8)
  val l: List[Int] = List(1)
  
  @Test def verifyLast() {
    // assert(last(Nil) === None)
    // assert(last(List()) === None)
    assert(last(l) === 1)
    assert(last(ls) === 8)
    intercept[NoSuchElementException] {
      last(Nil)
    }
    intercept[NoSuchElementException] {
      last(List())
    }
  }
 
  @Test def verifyPenultimate() {
    assert(penultimate(ls) === 5)
    intercept[NoSuchElementException] {
      penultimate(l)
    }
    intercept[NoSuchElementException] {
      penultimate(Nil)
    }
    intercept[NoSuchElementException] {
      penultimate(List())
    }
  }
  
  @Test def verifyNth() {
    assert(nth(0, l) === 1)
    assert(nth(2, ls) === 2)
    intercept[NoSuchElementException] {
      nth(1, l)
    }
    intercept[NoSuchElementException] {
      nth(6, ls)
    }
  }
  
  @Test def verifyLength() {
    assert(length(ls) === 6)
    assert(length(l) === 1)
    assert(length(Nil) === 0)
  }
  
  @Test def verifyReverse() {
    assert(reverse(ls) === List(8, 5, 3, 2, 1, 1))
    assert(reverse(l) === l)
    assert(reverse(Nil) === Nil)
  }
  
  @Test def verifyIsPalindrome() {
    assert(isPalindrome(List(1, 2, 3, 2, 1)) === true)
    assert(isPalindrome(ls) === false)
    assert(isPalindrome(l) === true)
  }
  
  @Test def verifyFlatten() {
    assert(flatten(ls) === ls)
    assert(flatten(List(List(1, 1), 2, List(3, List(5, 8)))) === ls)
  }
  
  @Test def verifyCompress() {
    assert(compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) === 
           List('a, 'b, 'c, 'a, 'd, 'e))
  }

  @Test def verifyPack() {
    assert(pack(List()) === List())
    // assert(pack(List()) === List(List()))
    assert(pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) ===
           List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)))
  }
  
  @Test def verifyEncode() {
    assert(encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) ===
           List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
  }
  
  @Test def verifyEncodeModified() {
    assert(encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) ===
           List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e)))
  }
}
