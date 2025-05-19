package com.example.erp

import cats.effect.*
import org.http4s.ember.server.*
import org.http4s.implicits.*
import com.example.controllers.{ArticleController, UserController}
import com.comcast.ip4s.ipv4
import com.example.services.UserService
import slick.jdbc.JdbcBackend.Database

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

//import slick.jdbc.PostgresProfile.api.*

import com.comcast.ip4s.port 
object Main extends IOApp:

  val httpApp = ( 
        ArticleController.routes
      ).orNotFound
      
  def run(args: List[String]): IO[ExitCode] =
  {
    val dbEc = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))
    Resource.make(IO(Database.forConfig("mydb")))(db => IO(db.close))
      .map { db =>
        implicit val ec: ExecutionContext = dbEc // Make implicit for services
        new AllRoutes(
          new UserController(new UserService(db)),
        )
      }
      .use { allRoutes =>
        EmberServerBuilder
          .default[IO]
          .withHost(ipv4"0.0.0.0")
          .withPort(port"8080")
          .withHttpApp(allRoutes.routes.orNotFound)
          .build
          .use(_ => IO.never)
      }
    }
