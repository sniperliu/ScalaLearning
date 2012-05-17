package org.leo.tetris

/**
 * Created by IntelliJ IDEA.
 * User: LiuHao
 * Date: 2010-4-15
 * Time: 20:48:14
 * To change this template use File | Settings | File Templates.
 */

import java.awt.{Color => AWTColor}
import java.awt.event.{KeyListener, KeyEvent}
import java.awt.event.{ActionEvent}
import java.util.{Random => JRandom}
import javax.swing.{Timer, AbstractAction}

import scala.swing._
// import event._

import Physical2D._

object STetris extends SimpleGUIApplication {

  Game.initGame

  override def top = frame
  val frame = new MainFrame {
    title = "Leo's Tetris"
    contents = new Panel {
      background = AWTColor.white
      preferredSize = (380, 600)
      override def paintComponent(g: java.awt.Graphics) {
        val g2d = g.asInstanceOf[java.awt.Graphics2D]
        g2d.setColor(AWTColor.white)
        g2d.fillRect(0, 0, size.width, size.height)
        val middle = (size.width / 2, size.height / 2)
        Game.drawBoard(g2d)
      }
    }

    peer.setFocusable(true)
    peer.addKeyListener(new KeyListener {
      override def keyPressed(e: KeyEvent) {
        e.getKeyCode match {
          case KeyEvent.VK_LEFT => Game.process(Game.left)
          case KeyEvent.VK_RIGHT => Game.process(Game.right)
          case KeyEvent.VK_UP => Game.process(Game.rotate)
          case KeyEvent.VK_DOWN => Game.process(Game.down)
//          case KeyEvent.VK_SPACE => game = game.drop
          case _ =>
        }
        repaint()
      }

      override def keyReleased(e: KeyEvent) {}
      override def keyTyped(e: KeyEvent) {}
    })

    val timer = new Timer(1000, new AbstractAction() {
      override def actionPerformed(e: ActionEvent) {
          Game.tick
          repaint()
      }
    })

    timer.start
  }

}


object Game {

  private[Game] class GameWorld {
    var activeBlock: Block = _
    var nextBlock: Block = _
    var mainBoard: Board = _
    var viewBoard: Board = _
  }

  private val world = new GameWorld()
  private val Blocks = List(IBar, Jay, El, OBox, Es, Tee, Zee)
  private val rand = new JRandom

  def halfBlockSize: Int = 10
  def preferredSize: Int = halfBlockSize * 2
  val margin: Int = 1

  def initGame {
    world.mainBoard = new Board(10, 25, (0, 0))
    world.viewBoard = new Board(6, 6, (24, 38))
    world.activeBlock = world.mainBoard.put(Blocks(rand.nextInt(Blocks.size)), Top)
    world.nextBlock = world.viewBoard.put(Blocks(rand.nextInt(Blocks.size)), Middle)
  }

  def rotate =
    world.activeBlock match {
      case Block(t, pos, angle) => Block(t, pos, angle + 90)
    }

  def down =
    world.activeBlock match {
      case Block(t, pos, angle) => Block(t, moveBy(0, -2, pos), angle)
    }

  def left =
    world.activeBlock match {
      case Block(t, pos, angle) => Block(t, moveBy(-2, 0, pos), angle)
    }

  def right =
    world.activeBlock match {
      case Block(t, pos, angle) => Block(t, moveBy(2, 0, pos), angle)
    }

  def tick = process(down)

  def process(blk: Block) = synchronized {
    if (!world.mainBoard.isBound(blk) && !world.mainBoard.isCollide(blk)) {
      world.activeBlock = blk
      if (world.mainBoard.isBottom(blk)) {
        world.mainBoard + blk
        world.mainBoard.removeFullLine()
        world.activeBlock = world.mainBoard.put(world.nextBlock.b, Top)
        world.nextBlock = world.viewBoard.put(Blocks(rand.nextInt(Blocks.size)), Middle)
      }
    }
  }

  private def draw[G <: java.awt.Graphics2D](g: G, p: (Int, Int), c: AWTColor, init: (Int, Int))(transform: ((Int, Int)) => (Int, Int) ) {
    // The operation includes the block transformation within itself coordinate system
    // Then transfer to Swing graphic coordination system
    val tmp = transform(p)
    val trans = (scale(halfBlockSize, _ : (Int, Int))).andThen(mirrorX(_)).andThen(moveBy(init._1, init._2, _))
    val (x, y) = trans(tmp)
    g.setColor(c)
    g.fill3DRect(x - halfBlockSize, y - halfBlockSize, preferredSize, preferredSize, true)
  }

  private def draw[G <: java.awt.Graphics2D](g: G, blk: BlockType, init: (Int, Int))(transform: ((Int, Int)) => (Int, Int) ) {
    blk.shape.foreach((p: (Int, Int)) => {
      draw(g, p, blk.color, init)(transform)
    })
  }

  def drawBlock[G <: java.awt.Graphics2D](g: G, blk: Block) {
    val (x, y) = (20, 540)
    blk match {
      case Block(blk, pos, angle) =>
        val trans = (Physical2D.rotate(angle, _ : (Int, Int) )).andThen(moveBy(pos._1, pos._2, _))
        draw(g, blk, (x, y))(trans)
    }
  }

  def drawBoard[G <: java.awt.Graphics2D](g: G) {
    val (x, y) = (20, 540)
    // Used to draw the border
    g.setColor(AWTColor.black)
    val (xmax, ymax) = (world.mainBoard.width, world.mainBoard.height)
    g.drawRect(x - margin - halfBlockSize, y - (ymax - 1) * preferredSize - margin - halfBlockSize,
               preferredSize * xmax + margin, preferredSize * ymax + margin)
    draw(g, world.mainBoard, (x, y))(identical)
    world.mainBoard.blocks.foreach(
      {case (k, v) =>
        v.foreach(x1 => draw(g, (x1, k), AWTColor.gray, (x, y))(identical))}
    )

    drawBlock(g, world.activeBlock)

    // Used to draw the border
    val (x1, y1) = (260, 160)
    g.setColor(AWTColor.black)
    val (xmax1, ymax1) = (world.viewBoard.width, world.viewBoard.height)
    g.drawRect(x1 - margin - halfBlockSize, y1 - (ymax1 - 1) * preferredSize - margin - halfBlockSize,
               preferredSize * xmax1 + margin, preferredSize * ymax1 + margin)
    val pos = world.viewBoard.position
    draw(g, world.viewBoard, (x, y))(moveBy(pos._1, pos._2, _))
    drawBlock(g, world.nextBlock)
  }
}

case class Block(b: BlockType, pos: (Int, Int), angle: Int)

sealed class Alignment
object Top extends Alignment
object Middle extends Alignment

case class Board(width: Int, height: Int, position: (Int, Int)) extends BlockType {
  var blocks: Map[Int, List[Int]] = Map()
  def shape =
    for {
      i <- List.range(0, width * 2, 2)
      j <- List.range(0, height * 2, 2)
    } yield {
      (i, j)
    }
  def color = AWTColor.white
  def put(b: BlockType, hAlign: Alignment): Block = {
    val y =
      hAlign match {
        case Top => position._2 + height * 2
        case Middle => position._2 + height
      }
    val x = position._1 + width
    b match {
      case OBox =>  Block(b, (x - 1, y - 3), 0)
      case _ => Block(b, (x - 2, y - 2), 0)
    }
  }

  def isOccupied(p: (Int, Int)): Boolean = {
    blocks.get(p._2) match {
      case Some(bs) => bs.exists(_ == p._1)
      case None => false
    }
  }

  def +(blk: Block) = {
    blk.b.shape.map((Physical2D.rotate(blk.angle, _ : (Int, Int))).andThen(moveBy(blk.pos._1, blk.pos._2, _))).foreach(
      (b: (Int, Int)) =>
        blocks.get(b._2) match {
          case Some(bs) => blocks = blocks + ((b._2, b._1 :: bs))
          case None => blocks = blocks + ((b._2, b._1 :: Nil))
        }
    )
  }

  def removeFullLine() {
    val key = blocks.find({case (k, v) => v.length == width}) match {
      case Some((k, _)) => k
      case None => -1
    }
    if (key != -1) {
      blocks = Map() ++ (blocks.filter({ case (k, _) => k != key })
                     .toList.map(t => if (t._1 > key) (t._1 - 2, t._2) else t))
      removeFullLine()
    }
  }

  def isCollide(blk: Block) = {
    blk.b.shape.map((Physical2D.rotate(blk.angle, _ : (Int, Int))).andThen(moveBy(blk.pos._1, blk.pos._2, _))).exists(
      b => isOccupied((b._1, b._2))
    )
  }

  def isBottom(blk: Block): Boolean = {
    blk.b.shape.map((Physical2D.rotate(blk.angle, _ : (Int, Int))).andThen(moveBy(blk.pos._1, blk.pos._2, _)))
               .exists(b => b._2 <= 0 || isOccupied((b._1, b._2 - 2)))
  }

  def isBound(blk: Block): Boolean = {
    blk.b.shape.map((Physical2D.rotate(blk.angle, _ : (Int, Int))).andThen(moveBy(blk.pos._1, blk.pos._2, _)))
               .exists( (block: (Int, Int)) => block._1 < 0 || block._1 == width * 2)
  }

}

/**
 * Basic Block Type
 * I, J, L, O, S, T, Z
 */
sealed abstract class BlockType {
  def shape: List[(Int, Int)]
  def color: AWTColor
}

case object IBar extends BlockType {
  override val shape: List[(Int, Int)] = List((-2,0), (0,0), (2,0), (4,0))
  override val color: AWTColor = AWTColor.cyan
}
case object Jay extends BlockType {
  override val shape: List[(Int, Int)] = List((-2,0), (0,0), (2,0), (2,-2))
  override val color: AWTColor = AWTColor.blue
}
case object El extends BlockType {
  override val shape: List[(Int, Int)] = List((-2,0), (0,0), (2,0), (-2, -2))
  override val color: AWTColor = AWTColor.orange
}
case object OBox extends BlockType {
  override val shape: List[(Int, Int)] = List((-1,1), (1,1), (1,-1), (-1,-1))
  override val color: AWTColor = AWTColor.yellow
}
case object Es extends BlockType {
  override val shape: List[(Int, Int)] = List((0,0), (2,0), (0,-2),(-2,-2))
  override val color: AWTColor = AWTColor.green
}
case object Tee extends BlockType {
  override val shape: List[(Int, Int)] = List((-2,0), (0,0), (2,0), (0,-2))
  override val color: AWTColor = new AWTColor(160, 92, 240)
}
case object Zee extends BlockType {
  override val shape: List[(Int, Int)] = List((-2,0), (0,0), (0,-2), (2,-2))
  override val color: AWTColor = AWTColor.red
}

object Physical2D {
  def identical(p: (Int, Int)) = p
  // Change later
  def rotate(delta: Int, p: (Int, Int)): (Int, Int) = {
    import scala.Math._
    val r = toRadians(delta)
    (  round((cos(r) * p._1 + sin(r) * p._2)).toInt
    ,  round((-sin(r)) * p._1 + cos(r) * p._2).toInt)
  }

  def mirrorX(p: (Int, Int)) = (p._1, -p._2)

  def scale(s: Int, p: (Int, Int)) = (s * p._1, s * p._2)

  def moveBy(x: Int, y: Int, pos: (Int, Int)) = (pos._1 + x, pos._2 + y)
//  {
//    if (delta % 90 == 0) (p._2, -p._1)
//    else {
//      import scala.Math._
//      val r = toRadians(delta)
//      (cos(r) * p._1 + sin(r) * p._2
//      , (-sin(r)) * p._1 + cos(r) * p._2)
//    }
//  }
}
