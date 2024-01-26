ThisBuild / scalaVersion := "3.3.1"

import xerial.sbt.Sonatype._
ThisBuild / sonatypeProjectHosting := Some(GitHubHosting("faveoled", "sous-title", "faveoled@yandex.com"))

// used as `artifactId`
ThisBuild / name := "sous-title"
// used as `groupId`
ThisBuild / organization := "io.github.faveoled"

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/faveoled/sous-title"),
    "scm:git@github.com:faveoled/sous-title.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id = "faveoled",
    name = "Fave Oled",
    email = "faveoled@yandex.com",
    url = url("https://github.com/faveoled")
  )
)
ThisBuild / homepage := Some(url("https://github.com/faveoled/sous-title"))

ThisBuild / licenses := Seq("APL2" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))

ThisBuild / description := "A scala library for parsing .srt files and strings format"


ThisBuild / publishMavenStyle := true
ThisBuild / versionScheme := Some("early-semver")
ThisBuild / publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
ThisBuild / dynverSonatypeSnapshots in ThisBuild := true
// publishTo := sonatypePublishToBundle.value
ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"

lazy val root = project.in(file(".")).
  aggregate(foo.js, foo.jvm, foo.native).
  settings(
    publish := {},
    publishLocal := {},
  )

lazy val foo = crossProject(JSPlatform, JVMPlatform, NativePlatform).in(file(".")).
  settings(
    name := "sous-title",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.17" % "test"
  ).
  jvmSettings(
    // Add JVM-specific settings here
  ).
  jsSettings(
    // Add JS-specific settings here
    scalaJSUseMainModuleInitializer := true,
  )
