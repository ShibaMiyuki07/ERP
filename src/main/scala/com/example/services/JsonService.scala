package com.example.services
import com.example.models.{Article}
import upickle.default._
object JsonService {
  def readJson(path : String) : List[Article] = {
    var articles : List[Article] = List()
    try{
      val jsonFile : String = getJsonFile("articles.json")
      articles= read[List[Article]](jsonFile)
    }
    catch {
      case e : Exception => throw e
    }
    articles
  }

  def getJsonFile(path : String) : String =
  {
    var jsonFile : String = ""
    try{
      jsonFile = os.read(os.pwd / path)
    }
    catch {
      case e : Exception => throw e
    }
    jsonFile
  }
}
