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
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        //...

        object Compose {
            val activity_compose by lazy { "androidx.activity:activity-compose:1.8.0" }
            val compose_bom by lazy { "androidx.compose:compose-bom:2023.03.00" }
            val compose_ui by lazy { "androidx.compose.ui:ui" }
            val compose_ui_graphics by lazy { "androidx.compose.ui:ui-graphics" }
            val compose_preview by lazy { "androidx.compose.ui:ui-tooling-preview" }
            val compose_material3 by lazy { "androidx.compose.material3:material3" }
        }
    }
}