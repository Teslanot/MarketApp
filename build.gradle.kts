
buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.40")
        classpath("com.google.gms:google-services:4.3.13")

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0")
    }
}


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}