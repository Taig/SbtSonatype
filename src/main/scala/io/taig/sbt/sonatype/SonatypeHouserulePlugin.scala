package io.taig.sbt.sonatype

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin
import xerial.sbt.Sonatype.autoImport._

object SonatypeHouserulePlugin extends AutoPlugin {
    object autoImport {
        val githubProject = settingKey[String]( "Github project identifier" )
    }

    import autoImport._

    override def requires = JvmPlugin

    override def trigger = allRequirements

    override def projectSettings: Seq[Def.Setting[_]] = Seq(
        githubProject := sys.error {
            "Please specify sbt configuration for githubProject"
        },
        homepage := Some( url( s"https://github.com/taig/${githubProject.value.toLowerCase}" ) ),
        licenses := Seq( "MIT" -> url( s"https://raw.githubusercontent.com/taig/${githubProject.value.toLowerCase}/master/LICENSE" ) ),
        organizationHomepage := Some( url( "http://taig.io" ) ),
        pomExtra := {
            <issueManagement>
                <url>https://github.com/taig/{githubProject.value.toLowerCase}/issues</url>
                <system>GitHub Issues</system>
            </issueManagement>
            <developers>
                <developer>
                    <id>Taig</id>
                    <name>Niklas Klein</name>
                    <email>mail@taig.io</email>
                    <url>http://taig.io/</url>
                </developer>
            </developers>
        },
        pomIncludeRepository := { _ => false },
        publishArtifact in Test := false,
        publishMavenStyle := true,
        publishTo := {
            val url = Some( "https://oss.sonatype.org/" )

            if( version.value.endsWith( "SNAPSHOT" ) ) {
                url.map( "snapshot" at _ + "content/repositories/snapshots" )
            }
            else {
                url.map( "release" at _ + "service/local/staging/deploy/maven2" )
            }
        },
        scmInfo := Some(
            ScmInfo(
                url( s"https://github.com/taig/${githubProject.value.toLowerCase}" ),
                s"scm:git:git://github.com/taig/${githubProject.value.toLowerCase}.git",
                Some( s"scm:git:git@github.com:taig/${githubProject.value.toLowerCase}.git" )
            )
        ),
        sonatypeProfileName := "io.taig"
    )
}