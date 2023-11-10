import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    object Hilt {
        private const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
        private const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

        val implementation = arrayListOf<String>().apply {
            add(android)
        }

        val kapt = arrayListOf<String>().apply {
            add(compiler)
        }

    }

    object Coroutine {
        private const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        private const val core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

        val implementation = arrayListOf<String>().apply {
            add(android)
            add(core)
        }
    }

    object Test {
        private const val core = "androidx.test:core:${Versions.test}"
        private const val rules = "androidx.test:rules:${Versions.test}"
        private const val jUnit = "androidx.test.ext:junit-ktx:${Versions.extJUnit}"
        private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

        val androidTestImplementation = arrayListOf<String>().apply {
            add(core)
            add(rules)
            add(jUnit)
            add(espressoCore)
        }
    }

    object JUnit {
        private const val junit = "junit:junit:${Versions.jUnit}"
        val testImplementation = arrayListOf<String>().apply {
            add(junit)
        }
    }

    object AndroidXSupport {
        private const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        private const val cardview = "androidx.cardview:cardview:${Versions.cardView}"
        private const val contraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        private const val recyclerview =
            "androidx.recyclerview:recyclerview:${Versions.recycelerView}"
        private const val viewpager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"
        private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        private const val viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        private const val navFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navHostKtx}"
        private const val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navHostKtx}"


        val implementations = arrayListOf<String>().apply {
            add(appCompat)
            add(cardview)
            add(coreKtx)
            add(viewModelKtx)
            add(navFragmentKtx)
            add(navUiKtx)
            add(contraintLayout)
            add(recyclerview)
            add(viewpager2)
        }

    }

    object Pagination {
        const val paging3 = "androidx.paging:paging-runtime-ktx:${Versions.paging3}"
    }


    object Network {
        private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        private const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        private const val okHttpInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        private const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
        private const val retrofitSerialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitSerialization}"

        val implementations = arrayListOf<String>().apply {
            add(retrofit)
            add(okHttp)
            add(okHttpInterceptor)
            add(serialization)
            add(retrofitSerialization)
        }

    }

    object MaterialDesign {
        val google = "com.google.android.material:material:${Versions.materialDesign}"
        val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    }


}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

