package com.example.controllers

import org.http4s.*
import org.http4s.dsl.io.*
import org.http4s.circe.CirceEntityCodec.*
import io.circe.generic.auto.*
import cats.effect.IO
import com.example.models.UserRow
import com.example.services.UserService

import scala.annotation.unused
import scala.concurrent.ExecutionContext


class UserController(@unused userService : UserService)(implicit @unused ec : ExecutionContext):
  val routes : HttpRoutes[IO] = HttpRoutes.of[IO]{
    case req @ POST -> Root  => {
      for{
        user <- req.as[UserRow]
        userId <- IO.fromFuture(IO(userService.createUser(user)))
        resp <- Created(Map("userId" -> userId))
      }
      yield resp
    }

    case GET -> Root =>{
      for{
        resp <- Ok(IO.fromFuture(IO(userService.getAllUsers)))
      } yield resp
    }
  }

