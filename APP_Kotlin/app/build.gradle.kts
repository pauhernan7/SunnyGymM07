plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.sunnygym"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.sunnygym"
        minSdk = 26
        targetSdk = 35
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

    // Configuración de compatibilidad para Java y Kotlin
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17   // Cambiado a 17
        targetCompatibility = JavaVersion.VERSION_17   // Cambiado a 17
    }

    kotlinOptions {
        jvmTarget = "17"  // Aseguramos que Kotlin también use JVM 17
    }
}

dependencies {
    // Android Core
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx)
    implementation(libs.activity.ktx)

    // CameraX
    implementation(libs.camera.core)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)
    implementation(libs.camera.camera2)

    // ML Kit Barcode Scanning
    implementation("com.google.mlkit:barcode-scanning:17.2.0")

    // Retrofit & Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Gràfics
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // ✅ TESTING (UNIT I INSTRUMENTATS)
    testImplementation("junit:junit:4.13.2") // Tests unitaris
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // Tests instrumentats
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation(kotlin("test"))




}
