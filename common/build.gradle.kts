plugins {
    id("lab5.java-conventions")
}

group = "ru.bardinpetr.itmo.lab5.common"

dependencies {
    implementation(project(":models"))

    implementation(Deps.jacksonDatabind)
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:${Deps.jacksonVersion}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Deps.jacksonVersion}")
}