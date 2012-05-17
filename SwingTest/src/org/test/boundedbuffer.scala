package org.test

object boundedbuffer {
  
  import scala.concurrent.ops._
  
  class BoundedBuffer[a](N: Int) {
    
    var in, out, n = 0
    val elems = new Array[a](N)
    
    def await(cond: => Boolean) = while (cond) { wait() }
    
    def put(x: a) = synchronized {
      await(n == N)
      elems(in) = x; in = (in + 1) % N; n+= 1
      if (n == 1) notifyAll
    }
    
    def get(): a = synchronized {
      await( n == 0 )
      val x = elems(out); out = (out + 1) % N; n -= 1; 
      if (n == N - 1) notifyAll
      x
    }
    
    def kill(delay: Int) {
      new java.util.Timer().schedule(
        new java.util.TimerTask {
          override def run() {
            println("[killed]")
            System.exit(0)
          }
        }, delay)
    }
    
  }

  def main(args: Array[String]) {
    
    val buf = new BoundedBuffer[String](10)
    
    var cnt = 0
    spawn { while(true) { cnt += 1; buf.put(cnt.toString) } }
    spawn { while(true) { println(buf.get()) } }
    buf.kill(4)
    
  }
}
    