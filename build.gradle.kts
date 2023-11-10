
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Versions.toolsBuildGradle apply false
    id("com.android.library") version Versions.toolsBuildGradle apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlinGradle apply false
    id("com.google.dagger.hilt.android") version Versions.hilt apply false

}

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:${Versions.gmsGradle}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navHostKtx}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinGradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradle}")
    }
    repositories {
        google()
        mavenCentral()
    }
}


