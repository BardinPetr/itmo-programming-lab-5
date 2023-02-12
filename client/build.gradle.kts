plugins {
    id("lab5.app-conventions")
}

group = "ru.bardinpetr.itmo.lab5.client"

application {
    mainClass.set("${group}.Main")
}

dependencies {
    implementation(project(":models"))
}