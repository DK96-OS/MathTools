plugins {
	kotlin("jvm")
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Coroutine support
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    
    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    
    // Use JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.7.2"))
    testImplementation('org.junit.jupiter:junit-jupiter-api')
    
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "failed") // not including "skipped"
    }
}
