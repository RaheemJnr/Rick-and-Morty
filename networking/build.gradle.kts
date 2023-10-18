plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
}

android {
    namespace = "com.rjnr.networkingg"
    compileSdk = ConfigurationData.compileSdk

    defaultConfig {
        minSdk = ConfigurationData.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    // kotlin/ktor
    implementation(Libs.Kotlin.serialization)
    implementation(Libs.Kotlin.ktor_core)
    implementation(Libs.Kotlin.content_negotiation)
    implementation(Libs.Kotlin.ktor_android_client)

    //test
    testImplementation(Libs.AndroidX.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.androidx_junit)
    androidTestImplementation(Libs.AndroidX.Test.androidx_espresso_core)
}