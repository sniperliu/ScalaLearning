/*********************************************
 * http://www.scala-lang.org/node/4845
 * 
 * http://aperiodic.net/phil/scala/s-99/
 *********************************************/
package org.leo.p99

object ListUtil {
  
  /***************************************
   * 
   * def last[A](l: List[A]): A = l.last
   * 
   * def last[A](l: List[A]): Option[A] = 
   *   l match {
   *     case Nil => None
   *     case x :: xs if xs != Nil => last(xs)
   *     case x :: xs => Some(x)
   *   }
   * 
   * *************************************/


  @throws(classOf[NoSuchElementException])
  def last[A](l: List[A]): A = 
    l match {
      case x :: Nil => x
      case _ :: tail => last(tail)
      case _ => throw new NoSuchElementException
    }
    
//  def last[A](l: List[A]): Option[A] = 
//    l match {
//      case x :: Nil => Some(x)
//      case _ :: tail => last(tail)
//      case _ => None
//    }

  @throws(classOf[NoSuchElementException])
  def penultimate[A](l: List[A]): A = 
    l match {
      case x :: _ :: Nil => x
      case _ :: tail => penultimate(tail)
      case _ => throw new NoSuchElementException
    }
  
  def nth[A](n: Int, l: List[A]): A = {
    def findNth[A](i: Int, l: List[A]): A = {
      if (i == n) l.head
      else findNth(i + 1, l.tail)
    } 
    findNth(0, l)
  }
  
  def length[A](l: List[A]): Int = 
    l.foldLeft(0)( (c, _) => c + 1 ) 
  
  def reverse[A](l: List[A]): List[A] = {
    // l.foldRight(List[A]()) { (x, xs) =>  xs ++ (x :: Nil)  }
    l.foldLeft(List[A]()) { (r, h) => h :: r }
  }
  
  def isPalindrome[A](ls: List[A]): Boolean = 
    ls == reverse(ls)
  
  def flatten(ls: List[Any]): List[Any] = 
    ls.flatMap(xs => xs match {
      case x : List[_] => flatten(x)
      case e => List(e)
    })
  
  def compress[A](ls: List[A]): List[A] =
    ls.foldRight(List[A]()) { (a, z) => if (!z.isEmpty && a == z.head) z else a :: z }
    // reverse(ls.foldLeft(List[A](ls.head)){ (z, l) => if (l == z.head) z else l :: z })
  
  def pack[A](ls: List[A]): List[List[A]] = 
    ls.foldRight(List[List[A]]()) { (a, z) => if (!z.isEmpty && z.head.head == a) (a :: z.head) :: z.tail else List(a) :: z }
  
  def encode[A](ls: List[A]): List[(Int, A)] = 
    pack(ls).map( xs => (length(xs), xs.head) )
  
  def encodeModified[A](ls: List[A]): List[Any] = 
    pack(ls).map( xs => if (length(xs) == 1) xs.head else (length(xs), xs.head) )
}
