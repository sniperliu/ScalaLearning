package org.leo.tutor.swing

import scala.swing._
import event._

object SMiner extends SimpleGUIApplication {
  
  def top = new MainFrame {
    title = "SMiner"
    
    val button = new Button {
      text = "Click me"
    }
    val label = new Label {
      text = "No button clicks registered"
    }
    
    contents = new BoxPanel(Orientation.Vertical) {
      contents += button
      contents += label
      border = Swing.EmptyBorder(30, 30, 10, 35)
    }
    
    listenTo(button)
    var nClicks = 0
    reactions += {
      case ButtonClicked(b) =>
        nClicks += 1
        label.text = "Number of button clicks: " + nClicks
    }
  }

}
