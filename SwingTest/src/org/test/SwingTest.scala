package org.test

import scala.swing._

object SwingTest extends SimpleGUIApplication {
  
  println("Init")
  override def top = new MainFrame {
    title = "SwingTest"
    contents = new Button {
      text = "Hello, World"
    }
  }

}
