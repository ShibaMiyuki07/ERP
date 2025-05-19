package com.example.erp

import cats.effect.*
import org.http4s.ember.server.*
import org.http4s.implicits.*
import com.example.controllers.{ArticleController}
import com.comcast.ip4s.ipv4

import slick.jdbc.PostgresProfile.api.*

import com.comcast.ip4s.port 
object Main extends IOApp:

  val httpApp = ( 
        ArticleController.routes
      ).orNotFound
      
  def run(args: List[String]): IO[ExitCode] =
    {
      val db = Database.forConfig("mydb")
      val query = sql"SELECT 1".as[Int]

      val action = IO.fromFuture(IO(db.run(query))).flatMap { result =>
        IO(println("DB result: " + result))
      }
      action *>
        EmberServerBuilder
              .default[IO]
              .withHost(ipv4"0.0.0.0")
              .withPort(port"8080")
              .withHttpApp(httpApp)
              .build
              .use(_ => IO.never)
              .as(ExitCode.Success)
    }
