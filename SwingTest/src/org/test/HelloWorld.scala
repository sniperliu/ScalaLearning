package org.test

import scala.swing._

object HelloWorld extends SimpleGUIApplication {
  def top = new MainFrame {
    title = "Hello, World"
//    contents = new Button("Click Me1")
    contents = new Button {
      text = "Click Me!"
    }
  }
}
