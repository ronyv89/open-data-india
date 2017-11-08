import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.github.ronyv89",
      scalaVersion := "2.11.8",
      version      := "0.1.0"
    )),
    name := "OpenDataIndia",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "org.apache.spark" %% "spark-sql" % "2.2.0" % "provided",
      "org.apache.spark" %% "spark-streaming" % "2.2.0" % "provided",
      "org.mongodb.spark" % "mongo-spark-connector_2.11" % "2.2.0",
      "com.typesafe" % "config" % "1.3.1"
    ),
  )
