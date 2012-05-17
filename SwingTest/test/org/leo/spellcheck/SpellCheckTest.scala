package org.leo.spellcheck

object SpellCheckTest {
  
  import scala.compat.Platform.currentTime
  import SpellCheck._
  
  implicit val bias = -1
  implicit val tt = false
  
  def spelltest(tests: Map[String, String])(implicit bias: Int, verbose: Boolean) = {  
    var (n, bad, unknown, start) = (0, 0, 0, currentTime)
    if (bias > 0) {
      for { (target, _) <- tests } {
          NWORDS += target -> bias
      }
    }
    for{ (target, wrongs) <- tests} {
      for {wrong <- wrongs.split(" ")} {
        n = n + 1
        val w = correct(wrong)
        bad = if (target != w)  bad + 1 else bad
        unknown = if (target != w && tests.keys.contains(w)) unknown + 1 else unknown
        if (verbose) 
          printf("correct(%s) => %s (%d); expected %s (%d)\n", 
                 wrong, w, (tests.get(w) match { case None => 1; case Some(x) => x }), target, NWORDS(target))
      }
    }
    (bad, n, bias, (100. - 100.*bad/n).toInt, unknown, (currentTime - start))
  }

  def main(args: Array[String]) {
    import SpellData._
    println(spelltest(tests1)(10, true))
  }
  
}
