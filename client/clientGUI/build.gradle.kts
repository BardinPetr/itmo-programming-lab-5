plugins {
    id("lab5.app-conventions")
}

group = "ru.bardinpetr.itmo.lab5.clientgui"

application.mainClass.set("$group.Main")

tasks.register<Jar>("fatJar") {
    manifest.attributes["Main-Class"] = application.mainClass
    archiveClassifier.set("fat")
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({ configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) } })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


dependencies {
    implementation(project(":client"))
    implementation(project(":models"))
    implementation(project(":common"))
    implementation(project(":network"))
    implementation(project(":events"))

    implementation("com.github.jiconfont:jiconfont-swing:1.0.0")
    implementation("com.github.jiconfont:jiconfont-font_awesome:4.7.0.1")
}