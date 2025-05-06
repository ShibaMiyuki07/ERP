package com.example.erp

import cats.effect.Sync
import cats.syntax.all.*
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import com.example.controllers.{ArticleController}
import com.example.models.{Article}


object ErpRoutes:
  
  def jokeRoutes[F[_]: Sync](J: Jokes[F]): HttpRoutes[F] =
    val dsl = new Http4sDsl[F]{}
    import dsl.*
    HttpRoutes.of[F] {
      case GET -> Root / "joke" =>
        for {
          joke <- J.get
          resp <- Ok(joke)
        } yield resp
    }

  def helloWorldRoutes[F[_]: Sync](H: HelloWorld[F]): HttpRoutes[F] =
    val dsl = new Http4sDsl[F]{}
    import dsl.*
    HttpRoutes.of[F] {
      case GET -> Root / "hello" / name =>
        for {
          greeting <- H.hello(HelloWorld.Name(name))
          resp <- Ok(greeting)
        } yield resp
    }

  def articleRoutes[F[_]:Sync](A : ArticleController[F]):HttpRoutes[F] = 
    val dsl = new Http4sDsl[F]{}
    import dsl.*
    HttpRoutes.of[F]{
      case GET -> Root / "articles" =>
        for{
          articles <- A.test(Article("Test","test"))
          resp <- Ok(articles)
        } yield resp
    }
