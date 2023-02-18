plugins {
    id("lab5.app-conventions")
}

group = "ru.bardinpetr.itmo.lab5.server"

application {
    mainClass.set("${group}.Main")
}

dependencies {
    implementation(project(":models"))
    implementation(project(":common"))
}