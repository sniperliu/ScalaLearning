package org.leo.tutor

import scala.collection.mutable.HashMap

import scala.xml._

case class Item(name: String, pn: String, price: Double) {
  
  private var iqnty = 0
  
  def qnty = iqnty
  
  def add(toAdd: Int) =  iqnty = iqnty + toAdd
  
  def take(howMany: Int) = {
    val toTake = if (howMany > iqnty) iqnty else howMany
    iqnty = qnty - toTake
    (toTake, iqnty)
  }
  
  def toXml = <item name={name} pn={pn} price={price.toString} 
                    qnty = {qnty.toString} />
}

object Sample extends Application {
  
  val inv1 = <update><item pn="a" qnty="25" /></update>
  val inv2 = <update>
               <item pn="o" qnty="45" />
               <item pn="b" qnty="4" />
             </update>
               
  println(ItemServer.update(inv1))
  println(ItemServer.update(inv2))
  
  val purchase = 
    <order>
      <item pn='a' qnty='20' />
      <item pn='b' qnty='30' />
      <item pn='o' qnty='35' />
      <item pn='na' qnty='23' />
    </order>
      
  println(ItemServer.order(purchase))
}

object ItemServer {
  val itemList = Item("Apple", "a", 0.25) :: 
                 Item("Banana", "b", 0.40) ::
                 Item("Orange", "o", 0.15) :: 
                 Nil
  val items = new HashMap[String, Item]
  
  itemList.foreach( i => items(i.pn) = i )
  
  def update(in: Elem) = {
    eachItem(in) {
      (e, pn, qnty) => 
        items.get(pn) match {
          case None => Text("")
          case Some(item) => <tmp>{item.add(qnty).toString}</tmp>
        }
    }
    currentInventory
  }
  
  def order(in: Elem) = {
    <order>
    {
      eachItem(in) {
        (e, pn, qnty) => 
          items.get(pn) match {
            case None => <not_found pn = {pn} />
            case Some(item) => {
              val took = item.take(qnty)
              <shipped pn={pn} ordered={qnty.toString} 
                shipped={took._1.toString}
                cost={(item.price * took._1).toString} />
            }
          }
      }
    }
    </order>
  }
  
  private def eachItem(in: Elem)(f: (Node, String, int) => Node): NodeSeq = {
    in.child.map {
      node => 
        node match {
          case n @ <item/> if (!n.attribute("pn").isEmpty && 
                               !n.attribute("qnty").isEmpty) 
            => f(n, n.attribute("pn").get.text,
                 Integer.parseInt(n.attribute("qnty").get.text))
          case _ => {Text("")}
        }
    }
  }
  
  def currentInventory = {
    val invValue = items.values.foldRight(0.0){
      (i, sum) => sum + i.price * i.qnty
    }
    
    <inventory value={invValue.toString}>
    {
      items.values.filter{i => i.qnty > 0}
                  .map {i => Text("\n    ") concat i.toXml}
                  .toList
    }
    </inventory>
  }
}
