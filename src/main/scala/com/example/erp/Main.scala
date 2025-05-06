package com.example.erp

import cats.effect.{IO, IOApp}

object Main extends IOApp.Simple:
  val run = ErpServer.run[IO]
