plugins {
	kotlin("jvm")
	id("jacoco")
	id("org.jetbrains.dokka") version "1.6.10"
}

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
	withJavadocJar()
	withSourcesJar()
}

tasks.named("sourcesJar", org.gradle.api.tasks.bundling.Jar::class) {
	duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

configure<PublishingExtension> {
	publications {
		register<MavenPublication>("gpr") {
			groupId = "io.github.dk96-os"
			artifactId = "lists"
			version = "0.3"
			from(components["java"])
		}
	}
}

dependencies {
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
	java.srcDirs("src/main/java", "src/main/kotlin")
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

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		xml.required.set(false)
		csv.required.set(false)
		html.outputLocation.set(layout.buildDirectory.dir("jacocoReport"))
	}
}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			limit {
				minimum = "0.8".toBigDecimal()
			}
		}
	}
}