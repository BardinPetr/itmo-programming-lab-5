plugins {
    id("lab5.java-conventions")
    id("application")
}

//val fatJar = task("fatJar", Jar::class) {
//    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
//    with(tasks["jar"] as CopySpec)
//}

//tasks.build {
//        dependsOn(fatJar)
//}
