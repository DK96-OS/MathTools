plugins {
	kotlin("jvm")
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Coroutine support
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    
    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.7.2"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
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
