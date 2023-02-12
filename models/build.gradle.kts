plugins {
    id("lab5.java-conventions")
    id("java-library")
}

group = "ru.bardinpetr.itmo.lab5.models"

dependencies {
    implementation("org.apache.bval:bval-jsr:1.1.2")
    implementation("javax.validation:validation-api:1.1.0.Final")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")
}