plugins {
    id("lab5.java-conventions")
    id("java-library")
}

group = "ru.bardinpetr.itmo.lab5.models"

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.14.2")
}