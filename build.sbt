name := """npg-contest-framework"""

version := "0.0.3"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

enablePlugins(DockerPlugin)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  ws
)

routesGenerator := InjectedRoutesGenerator

fork in run := true
