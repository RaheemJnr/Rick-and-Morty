plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //    // serialization
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
}

android {
    namespace = "com.rjnr.networking"
    compileSdk = ConfigurationData.compileSdk

    defaultConfig {
        applicationId = ConfigurationData.applicationId
        minSdk = ConfigurationData.minSdk
        targetSdk = ConfigurationData.targetSdk
        versionCode = ConfigurationData.versionCode
        versionName = ConfigurationData.versionName

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
}

dependencies {

    implementation(Libs.AndroidX.androidx_core)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.material)

    // kotlin serialization
    implementation(Libs.Kotlin.serialization)
    implementation(Libs.Kotlin.ktor_core)
    implementation(Libs.Kotlin.content_negotiation)

    //test
    testImplementation(Libs.AndroidX.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.androidx_junit)
    androidTestImplementation(Libs.AndroidX.Test.androidx_espresso_core)
}