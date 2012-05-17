package com.citi.gta

/**
 * The class is used to validate GTA mapping string match the 
 * mapping xml
 */
object MappingValidator {
  
  def validate(str: String, xmlName: String) = {

    Mapping(xmlName).get(str) match {
      case Some(group) => group
      case None => "No Match"
    }
    
  }
  
  def main(args: Array[String]) {
    println(validate("aaa", "C:\\APAC\\Mapping\\JPN_CHN_HKG_ CALCGROUP_MAPPING.xml"))
  }
}

object Mapping {
  
  def apply(name: String) = {
    new Mapping(name).toMap
  }
  
}

class Mapping(name: String) {
  
  val ROWMATCH_TAG = "row-match"
  val FIELDMATCH_TAG = "field-match"
  val OUTPUTROW_TAG = "output-row"
  val OUTPUTFIELD_TAG = "output-field"
  
  import scala.collection.immutable.{SortedMap, TreeMap}
  def toMap(): SortedMap[String, String] = {
    import scala.xml._
    val mapping = XML.loadFile(name) \ ROWMATCH_TAG
    
    var rowMatch = new TreeMap[String, String]()
//    mapping.toList.map((xml: NodeSeq) => {
//      println((xml \ FIELDMATCH_TAG).first \ "@matchvalue")
//      // println((xml \ FIELDMATCH_TAG).toList)
//      rowMatch = (rowMatch("@value") = 
//                  (xml \ OUTPUTROW_TAG \ OUTPUTFIELD_TAG).toString) 
//    })

    rowMatch
  }
  
}
