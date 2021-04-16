name := "cats-code"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.13.5"

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8", // source files are in UTF-8
  "-deprecation", // warn about use of deprecated APIs
  "-unchecked", // warn about unchecked type parameters
  "-feature", // warn about misused language features
  "-language:higherKinds", // allow higher kinded types without `import scala.language.higherKinds`
  "-Xfatal-warnings", // turn compiler warnings into errors
)

libraryDependencies += "org.typelevel" %% "cats-core" % "2.2.0"
