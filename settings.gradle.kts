/*
 * The settings file is used to specify which projects to include in your build
 * Detailed information about configuring a multi-project build in Gradle can be found
 * in the user manual at https://docs.gradle.org/7.4/userguide/multi_project_builds.html
 */
 
pluginManagement {
	plugins {
		kotlin("jvm") version "1.6.10"
	}
	repositories {
		google()
	    maven(url = "https://plugins.gradle.org/m2/")
	    mavenCentral()
	    gradlePluginPortal()
	}
}
rootProject.name = "MathTools"
include(
	":lists",
	":numbers",
	":statistics",
	":systems",
)