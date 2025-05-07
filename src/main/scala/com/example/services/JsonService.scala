package com.example.services
import com.example.models.{Article}
import upickle.default._
object JsonService {
  def readJson(path : String) : List[Article] = {
    val jsonFile = os.read(os.pwd / "articles.json")
    val articles = read[List[Article]](jsonFile)
    articles
  }

  given Reader[Article] = reader[ujson.Value].map { json =>
    val obj = json.obj
    new Article(
        obj("title").str,
        obj("content").str
    )
    }

    given Writer[Article] = writer[ujson.Value].comap { a =>
    ujson.Obj(
        "title" -> a.articleId,
        "content" -> a.articleName
    )
    }
}
