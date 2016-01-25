val demoPhantomTypes = project
  .in(file("."))
  .enablePlugins(AutomateHeaderPlugin, GitVersioning)

organization := "de.heikoseeberger"
name         := "demo-phantom-types"
licenses     += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

scalaVersion   := "2.11.7"
scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.8",
  "-encoding", "UTF-8"
)

unmanagedSourceDirectories.in(Compile) := List(scalaSource.in(Compile).value)
unmanagedSourceDirectories.in(Test)    := List(scalaSource.in(Test).value)

libraryDependencies ++= List(
  "org.scalacheck" %% "scalacheck" % "1.12.5" % "test",
  "org.scalatest"  %% "scalatest"  % "2.2.6"  % "test"
)

initialCommands := """|import de.heikoseeberger.demophantomtypes._""".stripMargin

git.useGitDescribe := true

import scalariform.formatter.preferences._
scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)

headers := Map("scala" -> de.heikoseeberger.sbtheader.license.Apache2_0("2016", "Heiko Seeberger"))
