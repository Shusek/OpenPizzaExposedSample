import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val h2_version: String by project
val exposed_version: String by project
val hiraki_version: String by project

plugins {
    application
    kotlin("jvm") version "1.3.61"
}

group = "org.openpizza"
version = "0.0.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}



repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    compile("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
    compile("io.ktor:ktor-server-netty:$ktor_version")
    compile("ch.qos.logback:logback-classic:$logback_version")
    compile("io.ktor:ktor-server-core:$ktor_version")
    compile("io.ktor:ktor-jackson:$ktor_version")

    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")

    compile("com.h2database:h2:$h2_version")
    compile("org.jetbrains.exposed:exposed-core:$exposed_version")
    compile("org.jetbrains.exposed:exposed-dao:$exposed_version")
    compile("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    compile("com.zaxxer:HikariCP:$hiraki_version")

    testCompile("io.ktor:ktor-server-tests:$ktor_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")
