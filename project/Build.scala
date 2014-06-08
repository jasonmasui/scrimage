import sbt._
import sbt.Keys._

object Build extends Build {

  val scrimageSettings = Seq(
    organization := "com.sksamuel.scrimage",
    version := "1.4.0-SNAPSHOT",
    scalaVersion := "2.11.0",
    crossScalaVersions := Seq("2.10.4", "2.11.1"),
    publishMavenStyle := true,
    publishArtifact in Test := false,
    parallelExecution in Test := false,
    scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8"),
    javacOptions ++= Seq("-source", "1.7", "-target", "1.7"),
    libraryDependencies ++= Seq(
      "org.slf4j"                     % "slf4j-api"             % "1.7.5",
      "org.imgscalr"                  % "imgscalr-lib"          % "4.2" % "test",
      "junit"                         % "junit"                 % "4.11" % "test",
      "org.scalatest"                 %% "scalatest"            % "2.1.6" % "test",
      "org.mockito"                   % "mockito-all"           % "1.9.5" % "test"
    )
  )

  lazy val root = Project("scrimage", file("."))
    .settings(scrimageSettings: _*)
    .aggregate(core, filters)

  lazy val core = Project("scrimage-core", file("core"))
    .settings(scrimageSettings: _*)
    .settings(name := "scrimage-core")

  lazy val filters = Project("scrimage-filters", file("filters"))
    .dependsOn(core)
    .settings(scrimageSettings: _*)
    .settings(name := "scrimage-filters")

  lazy val samples = Project("scrimage-samples", file("samples"))
    .dependsOn(core)
    .settings(scrimageSettings: _*)
    .settings(name := "scrimage-samples")
}