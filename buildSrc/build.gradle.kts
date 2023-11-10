import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins{
    `kotlin-dsl`
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

repositories {
    mavenCentral()
    google()
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "11"
    }
}