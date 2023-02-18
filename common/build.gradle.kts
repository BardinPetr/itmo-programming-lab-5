plugins {
    id("lab5.java-conventions")
}

group = "ru.bardinpetr.itmo.lab5.common"

dependencies {
    implementation(project(":models"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")

}