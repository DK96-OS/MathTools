plugins {
	kotlin("jvm")
}

configure<PublishingExtension> {
	publications {
		register<MavenPublication>("gpr") {
			groupId = "io.github.dk96-os"
			artifactId = "numbers"
			version = "0.1"
			from(components["java"])
		}
	}
}

dependencies {
	implementation(project(":lists"))
	implementation(project(":statistics"))

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Coroutine support
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    
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
    useJUnitPlatform() {
        excludeTags("slow")
    }
	maxParallelForks = 3
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