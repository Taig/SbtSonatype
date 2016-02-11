addSbtPlugin( "com.jsuereth" % "sbt-pgp" % "1.0.0" )

addSbtPlugin( "org.xerial.sbt" % "sbt-sonatype" % "1.1" )

name := "SbtSonatype"

normalizedName := "sonatype"

organization := "io.taig.sbt"

sbtPlugin := true

scalaVersion := "2.10.6"

version := "1.0.0"