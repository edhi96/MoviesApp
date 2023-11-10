plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

android {
    namespace = AppConfig.namespaceCore
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
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

    //network
    implementation(Dependencies.Network.implementations)

    // Hilt
    kapt(Dependencies.Hilt.kapt)
    implementation(Dependencies.Hilt.implementation)

    //coroutine
    implementation(Dependencies.Coroutine.implementation)

    //paging3
    implementation(Dependencies.Pagination.paging3)

}