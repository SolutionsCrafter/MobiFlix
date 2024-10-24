plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

}


android {
    namespace = "com.example.mobiflix"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mobiflix"
        minSdk = 28
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // AndroidX and Material dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.ui.text.android)
    implementation(libs.androidx.tools.core)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.contentpager)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Kotlin Standard Library
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // Coroutines support for Kotlin (if needed)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // ViewModel and LiveData (if using Jetpack components for MVVM architecture)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")

    // Jetpack Compose (optional, if using Compose for UI)
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")

    // Kotlinx Serialization (optional, if you need JSON serialization)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")

    implementation("com.android.volley:volley:1.2.1")

    implementation ("com.google.code.gson:gson:2.10.1")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.webkit:webkit:1.8.0")

    implementation ("androidx.browser:browser:1.5.0")


}
