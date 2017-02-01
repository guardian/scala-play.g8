
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

enablePlugins(PlayScala, RiffRaffArtifact, JDebPackaging)

mappings in Universal ++= (baseDirectory.value / "resources" *** ).get pair relativeTo(baseDirectory.value)

riffRaffPackageType := (packageBin in Debian).value
riffRaffUploadArtifactBucket := Option("riffraff-artifact")
riffRaffUploadManifestBucket := Option("riffraff-builds")
riffRaffManifestProjectName := "$riffRaff_manifest_project_name$"

resolvers += "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies ++= Seq(
  ws,
  "com.gu" %% "play-googleauth" % "$play_googleauth_version$",
  "org.scalatest" %% "scalatest" % "$scalatest_version$" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "$scalatestplay_version$" % Test
)

routesGenerator := InjectedRoutesGenerator

import com.typesafe.sbt.packager.archetypes.ServerLoader.Systemd
serverLoading in Debian := Systemd

debianPackageDependencies := Seq("openjdk-8-jre-headless")
maintainer := "Guardian Developers <dig.dev.software@theguardian.com>"
packageSummary := description.value
packageDescription := description.value

javaOptions in Universal ++= Seq(
    "-Dpidfile.path=/dev/null",
    "-J-XX:MaxRAMFraction=2",
    "-J-XX:InitialRAMFraction=2",
    "-J-XX:MaxMetaspaceSize=500m",
    "-J-XX:+UseConcMarkSweepGC",
    "-J-XX:+PrintGCDetails",
    "-J-XX:+PrintGCDateStamps",
    s"-J-Xloggc:/var/log/\${packageName.value}/gc.log"
)

initialize := {
  val _ = initialize.value
  assert(sys.props("java.specification.version") == "1.8",
    "Java 8 is required for this project.")
}