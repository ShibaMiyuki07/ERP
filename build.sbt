val Http4sVersion = "0.23.30"
val CirceVersion = "0.14.10"
val MunitVersion = "1.1.0"
val MunitCatsEffectVersion = "2.0.0"

lazy val root = (project in file("."))
  .settings(
    organization := "com.example",
    name := "erp",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "3.3.3",
    libraryDependencies ++= Seq(
      "org.postgresql" % "postgresql" % "42.5.0",
      "com.typesafe.slick" %% "slick" % "3.6.1",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.6.1",
      "com.lihaoyi" %% "os-lib" % "0.9.2",
      "com.lihaoyi" %% "upickle" % "3.1.3",
      "io.circe" %% "circe-core" % "0.14.6",
      "io.circe" %% "circe-generic" % "0.14.6",
      "io.circe" %% "circe-parser" % "0.14.6",
      "org.http4s"      %% "http4s-ember-server" % Http4sVersion,
      "org.http4s"      %% "http4s-ember-client" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "org.scalameta"   %% "munit"               % MunitVersion           % Test,
      "org.typelevel"   %% "munit-cats-effect"   % MunitCatsEffectVersion % Test,
    )
  )
