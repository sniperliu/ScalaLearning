package net.euler

object Problem17 {
  def main(args: Array[String]) = {
    val dict = Map(1 -> "One", 2 -> "Two", 3 -> "Three", 4 -> "Four", 
                   5 -> "Five", 6 -> "Six", 7 -> "Seven", 8 -> "Eight",
                   10 -> "Ten", 11 -> "Eleven", 12 -> "Twelve", 13 -> "Thirteen", 
                   14 -> "Fourteen", 15 -> "Fifteen", 16 -> "Sixteen", 17 -> "Seventeen", 
                   18 -> "Eighteen", 19 -> "Ninteen", 20 -> "Twenty", 30 -> "Thirty",
                   40 -> "Forty", 50 -> "Fifty", 60 -> "Sixty", 70 -> "Seventy", 
                   80 -> "Eighty", 90 -> "Ninty", 100 -> "Hundred", 1000 -> "Thousand")
    
    def findWord(item: Option[String]) = 
        item match {
          case Some(word) if word != null => println(word)
          case _ => println("No word found")
        }
    
    findWord(dict.get(10000))
  }
}
