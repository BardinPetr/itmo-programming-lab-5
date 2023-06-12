plugins {
    id("io.freefair.aggregate-javadoc") version "6.6.3"
}

group = "ru.bardinpetr.itmo.lab5"

tasks.register<Copy>("fatJarMerge") {
    print("merging")
    from(listOf("server", "client:clientGUI", "client:clientMain").map {
        project(it).buildDir.resolve("libs")
    })
    include("*fat.jar")
    into(buildDir.resolve("fatjar"))
}
