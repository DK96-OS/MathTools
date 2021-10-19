buildscript {
	repositories {
        mavenCentral()
        google()
    }
    dependencies {
    	classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
	}
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}
