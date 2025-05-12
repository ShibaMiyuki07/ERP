package com.example.erp

import cats.effect.*
import org.http4s.ember.server.*
import org.http4s.implicits.*
import com.example.controllers.{ArticleController}
import com.comcast.ip4s.ipv4

import com.comcast.ip4s.port 
import org.http4s.server.middleware.CORS
import org.http4s.{Method} 

object Main extends IOApp:

  val corsPolicy = CORS.policy
    .withAllowOriginAll
    .withAllowCredentials(false)
    .withAllowMethodsIn(Set(Method.GET, Method.POST, Method.PUT, Method.DELETE))
  val httpApp = corsPolicy( 
        ArticleController.routes
      ).orNotFound
      
  def run(args: List[String]): IO[ExitCode] =
  EmberServerBuilder
        .default[IO]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"8080")
        .withHttpApp(httpApp)
        .build
        .use(_ => IO.never)
        .as(ExitCode.Success)
