object Versions {
    const val ktlint = "0.45.2"
}

object Libs {
    // const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.1"

    object Kotlin {
//        private const val version = "1.7.0"
//        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        //...
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.6.1"
        val androidx_core by lazy { "androidx.core:core-ktx:1.12.0" }
        val androidx_lifecycle_runtime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2" }
        val material by lazy { "com.google.android.material:material:1.10.0" }

        object Compose {
            val activity_compose by lazy { "androidx.activity:activity-compose:1.8.0" }
            val compose_bom by lazy { "androidx.compose:compose-bom:2023.03.00" }
            val compose_ui by lazy { "androidx.compose.ui:ui" }
            val compose_ui_graphics by lazy { "androidx.compose.ui:ui-graphics" }
            val compose_preview by lazy { "androidx.compose.ui:ui-tooling-preview" }
            val compose_material3 by lazy { "androidx.compose.material3:material3" }
        }

        object Test {

            val junit by lazy { "junit:junit:4.13.2" }
            val androidx_junit by lazy { "androidx.test.ext:junit:1.1.5" }
            val androidx_espresso_core by lazy { "androidx.test.espresso:espresso-core:3.5.1" }
            val androidx_test_bom by lazy { "androidx.compose:compose-bom:2023.03.00" }
            val androidx_ui_test by lazy { "androidx.compose.ui:ui-test-junit4" }
            val androidx_ui_tooling_test by lazy { "androidx.compose.ui:ui-tooling" }
            val androidx_ui_test_manifest by lazy { "androidx.compose.ui:ui-test-manifest" }

        }
    }
}