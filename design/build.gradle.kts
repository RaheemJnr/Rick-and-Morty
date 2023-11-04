plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.rjnr.design"
    compileSdk = ConfigurationData.compileSdk

    defaultConfig {
//        minSdk = ConfigurationData.minSdk
//        targetSdk = ConfigurationData.targetSdk
//        versionCode = ConfigurationData.versionCode
//        versionName = ConfigurationData.versionName

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
    //compose
    implementation(Libs.AndroidX.Compose.activity_compose)
    implementation(platform(Libs.AndroidX.Compose.compose_bom))
    implementation(Libs.AndroidX.Compose.compose_material3)


}