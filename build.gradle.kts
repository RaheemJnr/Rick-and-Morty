// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) version "8.2.0" apply false
    alias(libs.plugins.jetbrains.kotlin) version "1.9.20" apply false
    alias(libs.plugins.android.library) version "8.2.0" apply false
    alias(libs.plugins.gitlab.detekt)
}

detekt {
    version = "1.23.3"
}
