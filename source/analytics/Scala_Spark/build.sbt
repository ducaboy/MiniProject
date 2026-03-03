ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.18"

lazy val root = (project in file("."))
  .settings(
    name := "Scala_Spark"
  )

libraryDependencies += "org.apache.spark" %% "spark-sql" % "4.1.1"
// Source: https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "4.1.1"
libraryDependencies += "org.postgresql" % "postgresql" % "42.7.9"