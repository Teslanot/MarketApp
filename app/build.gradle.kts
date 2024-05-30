plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.application")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.dan.marketapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dan.marketapp"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.firestore)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.loading.button.android)
    implementation(libs.glide)
    implementation(libs.circleimageview)
    implementation(libs.viewpagerindicator)
    implementation(libs.stepview)

    implementation(libs.androidx.navigation.fragment.ktx)

    implementation(libs.hilt.android.v2511)
    kapt(libs.hilt.android.compiler.v2511)
    implementation(libs.firebase.auth)
    implementation(libs.kotlinx.coroutines.play.services)

    // Apply the Kotlin KAPT plugin
    apply(plugin = "kotlin-kapt")
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}