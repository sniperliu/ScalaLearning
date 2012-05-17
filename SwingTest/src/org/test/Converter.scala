package org.test

import _root_.scala.swing._
import event._

object Converter extends SimpleGUIApplication {
  
  def newField = new TextField {
    text = "0"
    columns = 5
  }
  
  val celsius = newField
  val fahrenheit = newField
  
  val items = List("Lausanne", "Paris", "New York", "Berlin", "Tokio")
  val view = new ListView(items)
  
  def top = new MainFrame {
    title = "Convert Celsius / Fahrenheit"
    contents = new FlowPanel {
      contents += view
      contents += celsius
      contents += new Label(" Celsium = ")
      contents += fahrenheit
      contents += new Label(" Fahrenheit")
    }
    
    listenTo(celsius, fahrenheit)
    reactions += {
      case EditDone(`celsius`) => 
        val c = Integer.parseInt(celsius.text)
        val f = c * 9 / 5 + 32
        fahrenheit.text = f.toString
      case EditDone(`fahrenheit`) => 
        val f = Integer.parseInt(fahrenheit.text)
        val c = (f - 32) * 5 / 9
        celsius.text = c.toString
    }
  }
}
