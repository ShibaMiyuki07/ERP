package com.example.models

case class Article(articleId : String,articleName : String):
    
    def print() = println(s"$articleId $articleName")