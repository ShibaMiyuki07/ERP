package com.example.erp
import cats.effect.IO
import com.example.controllers.UserController
import org.http4s.HttpRoutes
import org.http4s.server.Router

class AllRoutes(
                 userRoutes: UserController
               ) {
  private val prefix = "/api/v1"

  val routes: HttpRoutes[IO] = Router(
    s"$prefix/users"    -> userRoutes.routes,
  )
}