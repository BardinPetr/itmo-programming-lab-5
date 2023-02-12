plugins {
    id("lab5.app-conventions")
}

group = "ru.bardinpetr.itmo.lab5.server"

application {
    mainClass.set("${group}.Main")
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.4.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.0")
}