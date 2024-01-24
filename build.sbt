ThisBuild / scalaVersion := "3.3.1"

import xerial.sbt.Sonatype._
sonatypeProjectHosting := Some(GitHubHosting("faveoled", "sous-title", "faveoled@yandex.com"))

// used as `artifactId`
name := "sous-title"
// used as `groupId`
organization := "io.github.faveoled"


licenses := Seq("APL2" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))

description := "A scala library for parsing .srt files and strings format"


publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
dynverSonatypeSnapshots in ThisBuild := true
// publishTo := sonatypePublishToBundle.value
sonatypeCredentialHost := "s01.oss.sonatype.org"

lazy val root = project.in(file(".")).
  aggregate(foo.js, foo.jvm).
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
