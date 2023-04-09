plugins {
    id("lab5.app-conventions")
}

group = "ru.bardinpetr.itmo.lab5.network"

dependencies {
    implementation(project(":common"))
    implementation(project(":models"))

    implementation("com.fasterxml.jackson.core:jackson-annotations:${Deps.jacksonVersion}")
}
