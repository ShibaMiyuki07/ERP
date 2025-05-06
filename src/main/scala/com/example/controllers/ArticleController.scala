package com.example.controllers
import cats.Applicative
import io.circe.{Encoder, Json}
import cats.syntax.all.*
import org.http4s.EntityEncoder
import org.http4s.circe.*
import com.example.models.{Article}

trait ArticleController[F[_]]:
    def test(article : Article): F[ArticleController.Test]

object ArticleController:
    final case class Test(test:Article) extends  AnyVal
    
    object Test
    given Encoder[Test] = new Encoder[Test]:
      final def apply(a: Test): Json = 
        a.test.toJson()
    given [F[_]]: EntityEncoder[F, Test] =
      jsonEncoderOf[F, Test]

    def impl[F[_]: Applicative]: ArticleController[F] = new ArticleController[F]:
        def test(article : Article): F[ArticleController.Test] = 
            Test(article).pure[F]