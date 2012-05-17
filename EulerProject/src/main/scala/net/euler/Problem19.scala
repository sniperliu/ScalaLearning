package net.euler

object Problem19 {
  def main(args: Array[String]) {
    import java.util.Calendar
    val cal = Calendar.getInstance
    cal.set(Calendar.DATE, 1)
    cal.set(Calendar.MONTH, Calendar.JANUARY)
    cal.set(Calendar.YEAR, 1901)
    
    val end = Calendar.getInstance
    end.set(Calendar.DATE, 2)
    end.set(Calendar.MONTH, Calendar.DECEMBER)
    end.set(Calendar.YEAR, 2000)
    
    var count = 0
    while(cal.compareTo(end) < 0) {
      if (cal.get(Calendar.DATE) == 1 && 
          cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
        println(cal.getTime)
        count += 1
      }
      cal.add(Calendar.DATE, 1)
    }
    println(count)
  }
}
