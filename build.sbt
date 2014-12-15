organization  := "com.jeff"

version       := "0.1"

scalaVersion  := "2.10.3"

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

scalacOptions ++= Seq("-encoding", "UTF-8", "-target:jvm-1.7")

javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.7", "-target", "1.7")

compileOrder := CompileOrder.JavaThenScala

externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false)

resolvers ++= Seq(
      "oschina" at "http://maven.oschina.net/content/groups/public"
)

libraryDependencies ++= {
  val akkaV = "2.3.0"
  val sprayV = "1.3.1"
  Seq(
    "io.spray"            %   "spray-can"     % sprayV  withSources(),
    "io.spray"            %   "spray-servlet" % sprayV,
    "io.spray" 			  %%  "spray-json"    % "1.2.6" withSources(),
    "io.spray"            %   "spray-routing" % sprayV,
    "org.json4s"          %%  "json4s-native"      % "3.2.4",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "ch.qos.logback"      %   "logback-classic" % "1.1.2",
    "com.typesafe.slick"  %%  "slick" % "2.1.0",
    "com.typesafe.slick" %% "slick-codegen" % "2.1.0" % "test" withSources(),
    "mysql" % "mysql-connector-java" % "5.1.6",
    "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
    "org.eclipse.jetty" % "jetty-webapp" % "8.1.10.v20130312",
    "junit" % "junit" % "4.4" % "test"
  )
}

excludeFilter in unmanagedResources := HiddenFileFilter || "web"

mainClass in (Compile, run) := Some("com.jeff.Boot")

seq(warSettings :_*)

webInfClasses in webapp := false

// set <project>/target/WebContent as the webapp destination directory
webappDest in webapp <<= target map  { _ / "webapp" }

publishArtifact in (Compile, packageBin) := false

