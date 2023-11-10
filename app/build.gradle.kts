plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
}

android {
    namespace = AppConfig.namespace
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = AppConfig.namespace
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.testInstrumentationRunner
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain {
            this.languageVersion.set(JavaLanguageVersion.of(11))
        }
    }

    kotlinOptions {
        jvmTarget = "11"
    }

}

dependencies {

    //core
    implementation(project(":core"))

    //androidX
    implementation(Dependencies.AndroidXSupport.implementations)

    //materialDesign
    implementation(Dependencies.MaterialDesign.google)
    implementation(Dependencies.MaterialDesign.glide)

    //test
    testImplementation(Dependencies.JUnit.testImplementation)
    androidTestImplementation(Dependencies.Test.androidTestImplementation)

    //network
    implementation(Dependencies.Network.implementations)

    // Hilt
    implementation(Dependencies.Hilt.implementation)
    kapt(Dependencies.Hilt.kapt)

    //coroutine
    implementation(Dependencies.Coroutine.implementation)

    //paging3
    implementation(Dependencies.Pagination.paging3)

}