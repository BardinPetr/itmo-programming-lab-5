plugins {
    id("java")
    id("io.freefair.lombok")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.javadoc {
//    setDestinationDir(projectDir.resolve("javadoc"))
//    sourceSets.addAll(sourceSets["main"].allSource)
}