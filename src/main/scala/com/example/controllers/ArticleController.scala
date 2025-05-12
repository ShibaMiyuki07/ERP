package com.example.controllers
import cats.effect.IO
import org.http4s.*
import org.http4s.dsl.io.*
import io.circe.syntax._
import org.http4s.circe._
import com.example.services.{JsonService}
import io.circe.generic.auto._ 
import io.circe.Json


object ArticleController:
    

    val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {

    case  GET -> Root / "articles" => 
      {
        try{
          Ok(JsonService.readJson("").asJson)
        }
        catch {
          case e : Exception => BadRequest(Json.obj("erreur" -> e.getMessage().asJson))
        }
      }
  }