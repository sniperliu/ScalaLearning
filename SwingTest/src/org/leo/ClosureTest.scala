package org.leo

object ClosureTest extends Application {

//  type Supercalifragilisticexpialidocious = java.lang.Integer
//  
//  implicit def Supercalifragilisticexpialidocious2Int(tehThingToConvert:Supercalifragilisticexpialidocious) = {
//    tehThingToConvert.asInstanceOf[Int]
//  }
//
//  trait ClosureTestMixin {
//    def makeAdder(tehAdditionValue:Supercalifragilisticexpialidocious):Function1[Int,Int]
//  }
//  
//  class AdderMaker extends ClosureTestMixin {
//    
//    def apply(tehAdditionValue:Supercalifragilisticexpialidocious):Function1[Int,Int] = {
//      new Function1[Int, Int] {
//        def apply(tehOtherAdditionValue:Int): Int = {
//          tehAdditionValue.$plus(tehOtherAdditionValue.asInstanceOf[Supercalifragilisticexpialidocious])
//        }
//      }
//    }
//    
//    def makeAdder(tehAdditionValue:Supercalifragilisticexpialidocious):Function1[Int,Int] = {
//      return this.apply(tehAdditionValue)
//    }
//
//  }
//  
//  val tehAdderMakerInstance = new AdderMaker()
//
//  val tehAdderPlus10 = tehAdderMakerInstance.makeAdder(10)
  
  def add(x: Int)(y: Int) = x + y
  def addPlus10_1 = add(10) _
  
  def addPlus10: Function1[Int, Int] = 10 + _
  println("Closure Test")
  println(addPlus10(9))
  println(addPlus10_1(9))
  // addPlus10 9
  
//  println(tehAdderPlus10.apply(9))
  // println(addPlus10.apply(9.toInt))

}
