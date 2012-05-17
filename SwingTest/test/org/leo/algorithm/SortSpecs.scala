package org.leo.algorithm

import org.specs._

class SortSpecs extends SpecificationWithJUnit {

  "Quick Sort" should {
    import Algorithm._
    // TODO def qsort = qsort1 _
    "return empty list if input is empty list" in {
      qsort1(List[Int]()) must_== Nil
      qsort2(List[Int]()) must_== Nil
    }
    "return single element list if input is only one element" in {
      qsort1(List(1)) must_== List(1)
      qsort2(List(1)) must_== List(1)
      qsort1(List('a')) must_== List('a')
      qsort1(List("abcd")) must_== List("abcd")
    }
    "return ordered Int List for simple case with Int List" in {
      qsort1(List(1,5,4,3,1)) mustEqual List(1,1,3,4,5)
      qsort2(List(1,5,4,3,1)) mustEqual List(1,1,3,4,5)
    }
    "return ordered Char List for simple case with Char List" in {
      qsort1(List('b', 'a', 'd', 'c', 'z', 'e')) mustEqual List('a', 'b', 'c', 'd', 'e', 'z')
      qsort2(List('b', 'a', 'd', 'c', 'z', 'e')) mustEqual List('a', 'b', 'c', 'd', 'e', 'z')
    }    
    "return same ordered list even sort multiple times" in {
      //TODO use scalacheck to generate the random scenario
      qsort1(qsort1(List(1,5,4,3,1))) mustEqual qsort1(List(1,5,4,3,1))
      qsort2(qsort2(List(1,5,4,3,1))) mustEqual qsort2(List(1,5,4,3,1))
    }
  }
  
}
