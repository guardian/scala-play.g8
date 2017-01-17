
name := "$name$"
description:= "$project_description$"

scalaVersion := "2.11.8"
organization := "com.gu"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-target:jvm-1.8",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)

scalacOptions in doc in Compile := Nil

lazy val root = (project in file(".")).enablePlugins(PlayScala, RiffRaffArtifact, JavaAppPackaging, UniversalPlugin)

mappings in Universal ++= (baseDirectory.value / "resources" *** ).get pair relativeTo(baseDirectory.value)

riffRaffPackageType := (packageZipTarball in config("universal")).value
riffRaffUploadArtifactBucket := Option("riffraff-artifact")
riffRaffUploadManifestBucket := Option("riffraff-builds")
riffRaffManifestProjectName := "$riffRaff_manifest_project_name$"

addCommandAlias("dist", ";riffRaffArtifact")

resolvers += "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies ++= Seq(
  ws,
  "com.gu" %% "play-googleauth" % "$play_googleauth_version$",
  "org.scalatest" %% "scalatest" % "$scalatest_version$" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "$scalatestplay_version$" % Test
)

routesGenerator := InjectedRoutesGenerator

initialize := {
  val _ = initialize.value
  assert(sys.props("java.specification.version") == "1.8",
    "Java 8 is required for this project.")
}