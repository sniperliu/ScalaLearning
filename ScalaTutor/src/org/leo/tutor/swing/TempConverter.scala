package org.leo.tutor.swing

import scala.swing._
import event._

object TempConverter extends SimpleGUIApplication {
  def top = new MainFrame {
    title = "Celsius/Fahrenheit Converter"
    val celsius = new TextField {
      columns = 5
    }
    val fahrenheit = new TextField {
      columns = 5
    }
    contents = new FlowPanel {
      contents += celsius
      contents += new Label { text = " C = " } 
      contents += fahrenheit
      contents += new Label { text = "F" }
    }
    
    listenTo(celsius, fahrenheit)
    reactions += {
      case EditDone(`celsius`) => 
        val c = celsius.text.toInt
        val f = c * 9 / 5 + 32
        fahrenheit.text = f.toString
      case EditDone(`fahrenheit`) => 
        val f = fahrenheit.text.toInt
        val c = (f - 32) * 5 / 9
        celsius.text = c.toString
    }
  }
}
