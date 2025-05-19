package com.example.services

import slick.ast.BaseTypedType
import slick.jdbc.PostgresProfile.api.*

import java.time.LocalDateTime
import java.sql.Timestamp
import slick.jdbc.{JdbcType, PostgresProfile}

trait DateTypeMapping {
  implicit val localDateTimeColumnType: JdbcType[LocalDateTime] & BaseTypedType[LocalDateTime] = MappedColumnType.base[LocalDateTime, Timestamp](
    ldt => Timestamp.valueOf(ldt),
    ts => ts.toLocalDateTime
  )
}
