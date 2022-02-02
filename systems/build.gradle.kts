plugins {
	kotlin("jvm")
	kotlin("plugin.serialization") version "1.6.10"
}

dependencies {
     // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Coroutine support
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

    // JSON Object Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    
    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.7.2"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

sourceSets.main {
	java.srcDirs("src/main/kotlin")
}

tasks.test {
    useJUnitPlatform()
	maxParallelForks = 4
	testLogging {
        events("passed", "failed") // not including "skipped"
    }
    reports {
    	junitXml.apply {
    		isOutputPerTestCase = true
    		mergeReruns.set(true)
    	}
    }
}