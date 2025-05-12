package com.example.models
import upickle.default.*

case class Article(var articleId : String,var articleName : String) derives ReadWriter{
    //require(articleId.nonEmpty,"Article Id cannot be empty")
}

    
