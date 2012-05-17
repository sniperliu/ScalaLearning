package org.leo

object RocketModule {

  sealed trait NoFuel
  sealed trait Fueled
  sealed trait NoO2
  sealed trait HasO2
  
  private[RocketModule] final case class Rocket[Fule, O2]
  
  def createRocket = Rocket[NoFuel, NoO2]()
  
  def addFuel[O2](x: Rocket[NoFuel, O2]) = Rocket[Fueled, O2]()
  
  def addO2[Fuel](x: Rocket[Fuel, NoO2]) = Rocket[Fuel, HasO2]()
  
  def launch(x: Rocket[Fueled, HasO2]) = "blastoff"
  
  implicit def toPiped[V](value: V) = new {
    def |>[R](f: V => R) = f(value)
  }
  
  def main(args: Array[String]) {
    val test1 = createRocket |> addO2 |> addFuel |> launch
    println(test1)
  }
}
