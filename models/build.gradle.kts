plugins {
    id("lab5.java-conventions")
    id("java-library")
}

group = "ru.bardinpetr.itmo.lab5.models"

dependencies {
    implementation(project(":events"))
    implementation("com.fasterxml.jackson.core:jackson-annotations:${Deps.jacksonVersion}")
}