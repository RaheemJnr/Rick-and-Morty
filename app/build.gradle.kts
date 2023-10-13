plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.rjnr.rickandmorty"
    compileSdk = ConfigurationData.compileSdk

    defaultConfig {
        applicationId = ConfigurationData.applicationId
        minSdk = ConfigurationData.minSdk
        targetSdk = ConfigurationData.targetSdk
        versionCode = ConfigurationData.versionCode
        versionName = ConfigurationData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Libs.AndroidX.androidx_core)
    implementation(Libs.AndroidX.androidx_lifecycle_runtime)
    //compose
    implementation(Libs.AndroidX.Compose.activity_compose)
    implementation(platform(Libs.AndroidX.Compose.compose_bom))
    implementation(Libs.AndroidX.Compose.compose_ui)
    implementation(Libs.AndroidX.Compose.compose_ui_graphics)
    implementation(Libs.AndroidX.Compose.compose_preview)
    implementation(Libs.AndroidX.Compose.compose_material3)
    //test
    testImplementation(Libs.AndroidX.Test.junit)
    androidTestImplementation(Libs.AndroidX.Test.androidx_junit)
    androidTestImplementation(Libs.AndroidX.Test.androidx_espresso_core)
    androidTestImplementation(platform(Libs.AndroidX.Test.androidx_test_bom))
    androidTestImplementation(Libs.AndroidX.Test.androidx_ui_test)
    debugImplementation(Libs.AndroidX.Test.androidx_ui_tooling_test)
    debugImplementation(Libs.AndroidX.Test.androidx_ui_test_manifest)
}