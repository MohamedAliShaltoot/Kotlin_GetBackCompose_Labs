plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.kotlin_getback_compose_lab1"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.kotlin_getback_compose_lab1"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}
kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.work.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
//    implementation ("androidx.work:work-runtime-ktx:2.9.0")
//
//    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
//
//    implementation ("com.github.bumptech.glide:glide:4.16.0")
//    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")
//   // kapt ("com.github.bumptech.glide:compiler:4.16.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


//// Retrofit
//    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
//
//// Lifecycle
//    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
//    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
//    //Coroutines Dependencies
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
//    //Retrofit
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
////GSON
//    implementation("com.google.code.gson:gson:2.10.1")
////Room
//    implementation ("androidx.room:room-ktx:2.6.1")
//            implementation ("androidx.room:room-runtime:2.6.1")
//            ksp ("androidx.room:room-compiler:2.6.1")

    implementation("androidx.navigation:navigation-compose:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
}