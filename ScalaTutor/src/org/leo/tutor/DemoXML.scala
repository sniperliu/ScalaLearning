package org.leo.tutor

object DemoXML {
  
  import scala.xml._
  def italics(node: Node): Unit = node match {
    case <i>{contents}</i> => println("Italics: " + contents)
    // case <title>{c @ _ *}</title> => for (child <- c) italics(child)
    case <node>{c @ _ *}</node> => for (val child <- c) italics(child)
    case _ => { }
  }
  
  def italics2(e: Node): Unit = {
    e match {
      case Elem(_, "node", _, _, c @ _ *) => c.foreach(k => italics2(k))
      case Elem(_, "i", _, _, c @ _ *) => println("Italic: " + e.text);
      case _ => { }
    }
  }

  def italics3(doc: Elem) = for (val ital <- doc \\ "i") println("Italic: " + ital.text)
  
  def go(title: String) = {
    val doc = 
      <node>
          <node>This is <i>some</i> text content.
          <node>This is <i>deeper</i> stuff.</node>
        </node>
        <node>I am some text.
          <title>I am <i>{title}</i>.</title>
          This is a sentence with an <i>italicized</i> entry.
        </node>
      </node> ;
    
    println("Looking at " + doc \\ "title")
    println("First version")
    italics(doc)
    println("Second version")
    italics2(doc)
    println("Third version")
    italics3(doc)
  }
  
  def main(args: Array[String]) {
    go("XML Patterns")
  }

}
