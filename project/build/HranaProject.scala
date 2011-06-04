import sbt._

class HranaProject(info: ProjectInfo) extends DefaultProject(info) with AutoCompilerPlugins
{
	override def useDefaultConfigurations = true

	val specs = "org.scala-tools.testing" % "specs_2.8.0.Beta1" % "1.6.2"
	val mockito = "org.mockito" % "mockito-all" % "1.8.1"
	val junit = "junit" % "junit" % "4.5"
	val thrift = "org.apache.thrift" % "libthrift" % "0.6.1"
	val gson = "com.google.gson" % "gson" % "1.7.1" from "http://google-gson.googlecode.com/files/google-gson-stream-1.7.1.jar"
	val slf4j_api = "com.slf4j.api" % "slf4j-api" % "1.6.0" from "http://repo2.maven.org/maven2/org/slf4j/slf4j-api/1.6.0/slf4j-api-1.6.0.jar"
	var slf4j_simple = "com.slf4j.simple" % "slf4j-simple" % "1.6.0" from "http://repo2.maven.org/maven2/org/slf4j/slf4j-simple/1.6.0/slf4j-simple-1.6.0.jar"
}