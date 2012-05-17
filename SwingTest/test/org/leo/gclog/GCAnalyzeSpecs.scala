package org.leo.gclog

import org.specs.SpecificationWithJUnit

class GCAnalyzeSpecs extends SpecificationWithJUnit("GCLogAnalyze") {

   "An Gabage Collection Log Analyzer" should {
     
     import GCLogAnalyze._
     
     "return the correct result for Partial GC records" in {
       
       val pgc = "53.758: [GC 53.758: [ParNew: 329024K->10240K(339264K), 0.1713284 secs] 355056K->48873K(1038336K), 0.1714695 secs]"       
       parse(pgc) must beSome(("53.758", "355056", "48873", "1038336", "0.1714695"))
       
     }
     
     "return the correct result for Full GC records" in {
       val fgc = "233176.700: [Full GC 233176.700: [Tenured: 699072K->699072K(699072K), 14.3126515 secs] 1038332K->987500K(1038336K), [Perm : 60605K->60605K(60672K)], 14.3128480 secs]"
       parse(fgc) must beSome(("53.758", "355056", "48873", "0.1714695"))
     }
   }
  
}
