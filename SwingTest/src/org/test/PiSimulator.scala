package org.test

import scala.swing._
import scala.swing.event._

class PiSimulator extends Applet {

  object ui extends UI with Reactor {
    def init() = {
      val button = new Button("Press here!")
      val text = new TextArea("Java Version: " +
                System.getProperty("java.version")+"\n")
      listenTo(button)
      reactions += {
        case ButtonClicked(_) => text.text += "Button Pressed!\n"
        case _ =>
      }
      contents = new BoxPanel(Orientation.Vertical) { contents.append(button, text) }
    }
  }
  
}

/* object PiSimulator {
  
  def calcPi(pi: Double, kicked: Double, total: Double): Double = {
    val point = (Math.random , Math.random)
    if (withinCircle(point)) {
      val temp = ((kicked + 1) / (total + 1)) * 4.0
      if ((pi - temp).abs < 0.00000001 && total > 1000) temp
      else calcPi(temp, kicked + 1, total + 1)
    } else {
      val temp = (kicked / (total + 1)) * 4.0
      if ((pi - temp).abs < 0.00000001 && total > 1000) temp
      else calcPi(temp, kicked, total + 1)
    }
  }
  
  def withinCircle(point: (Double, Double)): Boolean = 
    (point._1 - 0.5) * (point._1 - 0.5) + (point._2 - 0.5) * (point._2 - 0.5) <= 0.25
  
  def main(args: Array[String]) {
    
    println("Pi: " + calcPi(0, 0, 0))
    
//    var kicked: Double = 0
//    var total: Double = 0
//    
//    while(true) {            
//      val (x, y) = (Math.random , Math.random)
//      total = total + 1
//      if ((x - 0.5) * (x - 0.5) + (y - 0.5) * (y - 0.5) <= 0.25) {
//        kicked = kicked + 1
//      }
//      
//      val pi = (kicked / total) * 4 
//      println("total: " + total + ", kicked:  " + kicked + ", pi = " + pi)
//    }
  }
  
}*/
