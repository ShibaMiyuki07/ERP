package com.example.services
import com.example.models.{User, UserRow}
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.PostgresProfile.api.*

import scala.annotation.unused
import scala.concurrent.{ExecutionContext, Future}

class UserService(db : Database)(implicit @unused ec : ExecutionContext) {
    def createUser(user : UserRow): Future[Int] = {
      println(s"Attempting to create user: $user")

      // Insert only username and password, return generated ID
      db.run(
        (User.query.map(u => (u.userName, u.password))
          returning User.query.map(_.userId)) +=
          (user.userName, user.password)
      ).recoverWith {
        case e: Exception =>
          println(s"Database error: ${e.getMessage}")
          Future.failed(e)
      }
    }

    def getAllUsers: Future[Seq[UserRow]] = {
      db.run(User.query.result)
    }
    def getAllUser: Future[Either[String, Seq[UserRow]]] = {
      getAllUsers.map(Right(_))
        .recover {
          case e => Left(s"Database error: ${e.getMessage}")
        }
    }
}
