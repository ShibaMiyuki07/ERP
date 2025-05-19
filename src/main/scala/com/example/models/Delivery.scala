package com.example.models

import com.example.services.DateTimeMapping

import java.time.LocalDateTime
import slick.jdbc.PostgresProfile.api.*
import slick.lifted.Tag

case class DeliveryRow(
  deliveryId: Int,
  commandId: Int,
  deliveryDate: LocalDateTime,
  deliveryAddress: String,
  status: String
)

class Delivery(tag : Tag) extends Table[DeliveryRow](tag,"deliveries") with DateTimeMapping{
  def deliveryId = column[Int]("deliveryId",O.PrimaryKey)
  def commandId = column[Int]("commandId")
  def deliveryDate = column[java.time.LocalDateTime]("deliveryDate")
  def deliveryAddress = column[String]("deliveryAddress")
  def status = column[String]("status")
  def * = (
    deliveryId,
    commandId,
    deliveryDate,
    deliveryAddress,
    status
  ).mapTo[DeliveryRow]
}

val delivery = TableQuery[Delivery]

