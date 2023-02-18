plugins {
    id("lab5.app-conventions")
}

group = "ru.bardinpetr.itmo.lab5.client"

application {
    mainClass.set("${group}.Main")
}

dependencies {
    implementation(project(":models"))
    implementation(project(":common"))
    implementation(project(":server"))

    implementation("com.fasterxml.jackson.core:jackson-databind:${Deps.jacksonVersion}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Deps.jacksonVersion}")
}