
// Уровень проекта
buildscript {
    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.google.services)
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    alias(libs.plugins.google.gms.google.services) apply false
}