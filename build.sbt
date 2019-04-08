organization := "marklister"
name := "minimal-router-example"
version := "0.1.0"

scalaVersion := "2.12.8"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies ++= Seq(
  "com.github.outwatch" % "outwatch" % "master-SNAPSHOT",
  "com.github.marklister" % "minimal-router" % "master-SNAPSHOT",
  "org.scala-js" %%% "scalajs-dom" % "0.9.6",
  "org.scalatest" %%% "scalatest" % "3.0.5" % Test
)

enablePlugins(ScalaJSBundlerPlugin)

scalacOptions += "-P:scalajs:sjsDefinedByDefault"
useYarn := true // makes scalajs-bundler use yarn instead of npm
//requiresDOM in Test := true
scalaJSUseMainModuleInitializer := true
scalaJSModuleKind := ModuleKind.CommonJSModule 