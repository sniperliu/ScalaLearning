package org.leo.tutor

import javax.swing._

object SwingTest extends JFrame("Greetings") {
  def main(args: Array[String]) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    add( new JLabel("Hello World"))
    pack()
    setVisible(true)
  }
}
