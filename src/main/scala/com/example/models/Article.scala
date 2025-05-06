package com.example.models
import io.circe.{Json}

case class Article(articleId : String,articleName : String):
    
    def print() = println(s"$articleId $articleName")

    def toJson():Json = 
        Json.obj(
            "articleId" -> Json.fromString(this.articleId),
            "articleName" -> Json.fromString(this.articleName)
        )