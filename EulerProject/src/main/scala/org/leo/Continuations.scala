package org.leo

object Main {

  //type Cont[A] = (A => Unit) => Unit;

  /** a class for "monadic artifacts" */
  case class M[+A](in: (A=>Any)=>Any) {
    def map[B](f: A => B)        = bind(this, {x: A => unit(f(x))});
    def flatMap[B](f: A => M[B]) = bind(this, f);
  }
  
  def unit[A](x: A) = 
    M((k:(A=>Any)) => k(x));
    
  def bind[A,B](m:M[A], f:A=>M[B]):M[B] = 
    M(k => m.in (x => f(x).in(k)));

  def callCC[A](e:(A=>M[A])=>M[A]):M[A] =   
    M(k => e(a => M(ignored => k(a))).in(k));

    // Michel's Explanation:

    // The argument given to "e" must be a reified continuation, i.e.
    // a function which, when invoked with some parameter, replaces
    // the current continuation with the one of the call to callCC. In
    // the code above, "ignored" is the current continuation, and "k"
    // is the one of the call to callCC. Finally, "a" is the value
    // which will be "returned" by the call to callCC.

    // We also have to give a continuation to the whole callCC
    // expression, because it is perfectly possible that the reified
    // continuation is never invoked. This continuation is "k", quite
    // naturally. We therefore pass it to e's "input".
  
  // the "final continuation"
  def show[A]():(A)=>Unit = {x:A => Console.println(x)}

  // convenience
  def show[A](m:M[A]):Unit = { m.in (show()) }

  // convenience^2
  def showshow[A](m:M[M[A]]):Unit = { m.in (k => k.in (show())) }

  def reverse(s:String):String = {
    val sb = new StringBuffer();
    var i = s.length(); while(i > 0) {
      i = i - 1;
      sb.append(s.charAt(i));
    }
    sb.toString();
  }

  def main(args:Array[String]): Unit = {

    // example 1
    val p = unit[Int](3);              // computation that simply returns 3
    Console.println("example 1 (unit)");
    show(p);                                     // same as `p.in (show())'
    Console.println("---");


//    // example 2a
//    val m = unit[String]("42");                   // result of Deep Thought
//    val f = {x: String => M(k("meaning of life:"+x))};
//    val n = bind(m, f);
//    Console.println("example 2a (bind)");
//    show(n);                                     // same as `n.in (show())'
//    Console.println("---");
//
//
//    // example 2b (same as 2a, but with convenient syntax)
//    val _n2 = for(val x <- unit("42");
//          val n <- M(k("meaning of life:"+x))) 
//          yield n;
//    
//    Console.println("example 2b (bind, nice syntax)");
//    show(_n2);                                 // same as `_n2.in (show())'
//    Console.println("---");
//
//
//    // example 3
//    val _r = for(val x <- n;
//         val m <- unit(reverse(x))) yield m;
//    //           same as `val _r = bind(n, {x:String => unit(reverse(x))})'
//    Console.println("example 3 (composition)");
//    show(_r);      //same as `_r.in (show())'
//    Console.println("---");
//
//
//    // example 4
//    def meaning(_x:M[String]=>M[M[String]]):M[M[String]] = {
//      bind(unit(unit("42")), _x)
//    }
//        
//    val someComputation = 
//      meaning({x => unit(bind(x, {z:String => unit("the meaning of life is "+z)}))});
//
//    Console.println("example 4 (why callCC matters)");
//    showshow( someComputation ) ; //.in {k => k.in (show())};
//
//    
//    showshow( callCC(meaning) )                      // look Ma, no scheme!
  }
}
