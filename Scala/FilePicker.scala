package com.citi.gta

import java.io.File

object FilePicker {
  
  def compareNameWithDate(f: File): Boolean = {
    val NamePattern = "(^\\d{4}-\\d{2}-\\d{2}.*)".r
    //val NamePattern = "(^\\d{8}.*)".r
    //TODO have a method to separate the file name from absolute string
    // val names = f.getName.split(File.pathSeparator)
    // names(names.length - 1) match {
    f.getName match {
      case NamePattern(name) if name > "2007-11-23" => true
      case _ => false
    }
    // if (names(names.length - 1) > "20071123") true else false
  }
  
  def pickFile(rule: File => Boolean)(src: List[File]): List[File] = {
    src match {
      case Nil => Nil
      case f :: Nil if f.isFile => if (rule(f)) f :: Nil else Nil
      case f :: fs if f.isFile => 
        (if (rule(f)) f :: Nil else Nil) ++ pickFile(rule)(fs)
      case f :: Nil if f.isDirectory => 
        pickFile(rule)(List.fromArray(f.listFiles))
      case f :: fs if f.isDirectory => 
        pickFile(rule)(List.fromArray(f.listFiles)) ++ pickFile(rule)(fs)
      case _ => Nil
    }
  }

  def main(args: Array[String]) {
    val target = "C:\\Users\\hl26184\\Desktop\\STE\\locale"
    val src = "C:\\Users\\hl26184\\Desktop\\STE\\4.1.52.1594-Fixpack-Jan-12\\Schema\\Oracle"
    val appsrc = "C:\\Users\\hl26184\\Desktop\\STE\\4.1.52.1594-Fixpack-Jan-12\\ApplicationData"
    val locsrc = "C:\\Users\\hl26184\\Desktop\\STE\\4.1.52.1594-Fixpack-Jan-12\\LanguagePack\\Localization\\Oracle"
    val locsrc1 = "C:\\Users\\hl26184\\Desktop\\STE\\4.1.52.1594-Fixpack-Jan-12\\LanguagePack\\Localization\\Japanese"
    val locsrc2 = "C:\\Users\\hl26184\\Desktop\\STE\\4.1.52.1594-Fixpack-Jan-12\\LanguagePack\\Localization\\ChineseTraditional"
    import util.ResUtil.copyFileToDir
    (pickFile(compareNameWithDate)(List(new File(locsrc), new File(locsrc1), new File(locsrc2)))).foreach((x: File) => copyFileToDir(x, target))
    //(pickFile(compareNameWithDate)(List(new File(locsrc1)))).foreach((x: File) => println(x.getAbsolutePath))
    //(pickFile(compareNameWithDate)(List(new File(locsrc2)))).foreach((x: File) => println(x.getAbsolutePath))
  }
  
}
