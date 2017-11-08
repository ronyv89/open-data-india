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
      "org.elasticsearch" % "elasticsearch-spark-20_2.11" % "5.6.3",
      "com.typesafe" % "config" % "1.3.1"
    ),
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    },
    resolvers += "mvnrepository" at "http://mvnrepository.com/artifact/",
    // resolvers += "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven",
    resolvers += "Spark Packages Repo" at "http://dl.bintray.com/spark-packages/maven",
    fork := true
  )
