package com.example.controllers
import cats.effect.IO
import org.http4s.*
import org.http4s.dsl.io.*
import io.circe.syntax.*
import org.http4s.circe.*
import com.example.models.{Article}
import io.circe.{Encoder, Decoder, HCursor, Json}
import com.example.services.{JsonService}

object ArticleController:
    
    implicit val decoder: EntityDecoder[IO, Article] = jsonOf[IO, Article]
    given Encoder[Article] with
        def apply(a: Article): Json = Json.obj(
            "title" -> Json.fromString(a.articleId),
            "content" -> Json.fromString(a.articleName)
        )


    given Decoder[Article] with
        def apply(c: HCursor): Decoder.Result[Article] =
        for
            title <- c.downField("title").as[String]
            content <- c.downField("content").as[String]
        yield new Article(title, content)

    val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {

    case  GET -> Root / "articles" =>
      {
        for
        res <- Ok(JsonService.readJson("").asJson)
        yield res
      }
  }