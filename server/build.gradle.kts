plugins {
    id("lab5.app-conventions")
}

group = "ru.bardinpetr.itmo.lab5.server"

//task<Jar>("fatJar").manifest.attributes["Main-Class"] = "$group.Main"

application.mainClass.set("$group.Main")

dependencies {
    implementation(project(":models"))
    implementation(project(":common"))
    testImplementation(project(mapOf("path" to ":common")))
    testRuntimeOnly(project(":common"))
}