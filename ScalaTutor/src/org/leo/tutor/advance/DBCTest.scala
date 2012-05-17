package org.leo.tutor.advance

trait Contracted {
  class AssertFailed extends Error
  
  type Conds = List[() => Boolean]
  
  protected case class Contract(reqs: Conds, enss: Conds) {
    def require(test: => Boolean) = Contract((() => test) :: reqs, enss)
    def ensure(test: => Boolean) = Contract(reqs, (() => test) :: enss)
    
    def in[T](body: => T): T = {
      for (r <- reqs.reverse if(!r())) throw new AssertFailed()
      val ret = body
      for (e <- enss.reverse if(!e())) throw new AssertFailed()
      ret
    }
  }
  
  def require(test: =>boolean) = Contract((()=>test)::Nil,Nil)
  def ensure(test: =>boolean) = Contract(Nil,(()=>test)::Nil)
}

class Account(b: Int) extends Contracted
{
    var balance = b
 
    def withdraw(amount: Int)
    {
        val old_balance=balance
        (
            require(amount>0)
            require(balance-amount>=0)
            ensure(old_balance-amount==balance)
        ) in {
            balance-=amount
        }
    }
    
    def credit(amount: Int)
    {
        val old_balance=balance
        (//Note these can come in any order, although all ensures then all requires in more logical
            ensure(balance<=1000) //Fairly arbitrary restriction as an example
            ensure(balance>=0)
            require(amount>0)
            ensure(old_balance+amount==balance)
        ) in {
            balance+=amount
        }
    }
    
    def print()
    {
        println("Balance:"+balance)
    }
}

object DBCTest extends Application {
  val a=new Account(100)
  a.print()
  a.withdraw(50)
  a.print()
  a.withdraw(50)
  a.print()
  a.credit(100)
  a.print()
  a.credit(1000)
  a.print()
}
