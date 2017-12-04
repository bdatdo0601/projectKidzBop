import org.gradle.platform.base.Application
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


group = "NLP"
version = "1.0-SNAPSHOT"



buildscript {

    var kotlinVersion: String by extra
    kotlinVersion = "1.2.0"


    repositories {
        mavenCentral()

    }

    dependencies {
        classpath(kotlinModule("gradle-plugin", kotlinVersion))
    }

    apply<ApplicationPlugin>()

    configure<ApplicationPluginConvention> {
        mainClassName = "main.Main"
    }

}

apply {
    plugin("java")
    plugin("kotlin")
    plugin("application")
    plugin("idea")
}


val kotlinVersion: String by extra


repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
    jcenter()
}

dependencies {
    compile(kotlinModule("stdlib-jdk8", kotlinVersion))
    testCompile("junit", "junit", "4.12")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-reflect
    compile(group = "org.jetbrains.kotlin", name = "kotlin-reflect", version = "1.2.0")
    compile("com.github.npryce:konfig:1.6.2.0")
    compile("com.google.code.gson:gson:2.8.2")
    compile("com.github.kittinunf.fuel:fuel:1.12.0")
    compile("com.github.kittinunf.fuel:fuel-gson:1.12.0")
    compile("com.github.adamint:spotify-web-api-kotlin:2.1")
    compile("com.github.sachin-handiekar:jMusixMatch:jMusixMatch-1.1.4")
    compile(group = "org.apache.opennlp", name = "opennlp-tools", version = "1.8.3")
    compile("com.google.cloud:google-cloud-language:1.12.0")
    compile("com.github.TWinters:Datamuse-Java:-SNAPSHOT")

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

configure<ApplicationPluginConvention> {
    mainClassName = "main.Main"
}



