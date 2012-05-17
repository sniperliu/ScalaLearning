package com.citi.gta

import java.net.URLDecoder
import java.net.URLEncoder

object URLShowcase {

  def main(args: Array[String]): Unit = { 
    // println(URLDecoder.decode("https://fhwone-stg.webbank.ssmb.com/psp/eppautg1_newwin/EMPLOYEE/EMPL/e/?url=https%3a%2f%2ffhwone-stg.webbank.ssmb.com%2fpsc%2feppautg1%2fEMPLOYEE%2fEMPL%2fs%2fWEBLIB_CGP.CGP_LINK_HELPER.FieldFormula.iscript_linkhelper_lngmap%3fmap%3dGTA%26cgplnk%3dhttps%3A%2F%2Fctmseast-stg.webbank.ssmb.com%2Fctms%2FredirectToTORF.jsp%3Ftarget%3DUSERSWHOCANBECOMEME&FolderPath=PORTAL_ROOT_OBJECT.CGP_TA_APAC&IsFolder=false&IgnoreParamTempl=FolderPath%2cIsFolder"))
    
    println(URLEncoder.encode("https%3A%2F%2Fctmseast-stg.webbank.ssmb.com%2Fctms%2FredirectToTORF.jsp%3Ftarget%3DUSERSWHOCANBECOMEME"))
  }

}
