homepage := Some( url( s"https://github.com/taig/sbtsonatype" ) )

licenses := Seq( "MIT" -> url( s"https://raw.githubusercontent.com/taig/sbtsonatype/master/LICENSE" ) )

organizationHomepage := Some( url( "http://taig.io" ) )

pomExtra := {
    <issueManagement>
        <url>https://github.com/taig/sbtsonatype/issues</url>
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
}

pomIncludeRepository := { _ => false }

publishArtifact in Test := false

publishMavenStyle := true

publishTo := {
    val url = Some( "https://oss.sonatype.org/" )

    if( version.value.endsWith( "SNAPSHOT" ) ) {
        url.map( "snapshot" at _ + "content/repositories/snapshots" )
    }
    else {
        url.map( "release" at _ + "service/local/staging/deploy/maven2" )
    }
}

scmInfo in ThisBuild := Some(
    ScmInfo(
        url( s"https://github.com/taig/sbtsonatype" ),
        s"scm:git:git://github.com/taig/sbtsonatype.git",
        Some( s"scm:git:git@github.com:taig/sbtsonatype.git" )
    )
)

sonatypeProfileName := "io.taig"

startYear := Some( 2016 )