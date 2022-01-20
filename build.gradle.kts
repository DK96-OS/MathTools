buildscript {
	repositories {
        mavenCentral()
        google()
    }
    dependencies {
    	classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
   	}
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}
