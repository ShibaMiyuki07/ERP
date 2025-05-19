package com.example.models

import slick.jdbc.PostgresProfile.*
import slick.lifted.{TableQuery, Tag}
import slick.jdbc.PostgresProfile.api._

case class UserRow(
  userId : Option[Int] = None,
  userName : String,
  password : String
)

class User(tag : Tag) extends Table[UserRow](tag,"users"){
  def userId = column[Int]("userid",O.PrimaryKey,O.AutoInc)
  def userName = column[String]("username")
  def password = column[String]("password")
  def * = (userId.?, userName, password).mapTo[UserRow]
}

object User{
  val query = TableQuery[User]
}

