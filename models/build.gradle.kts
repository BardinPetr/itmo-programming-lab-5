plugins {
    id("lab5.java-conventions")
    id("java-library")
}

group = "ru.bardinpetr.itmo.lab5.models"

dependencies {
    implementation("org.apache.bval:bval-jsr:1.1.2")
    implementation("javax.validation:validation-api:1.1.0.Final")
}