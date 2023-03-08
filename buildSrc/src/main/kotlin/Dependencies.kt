object Versions {

    const val versionCode = 1
    const val versionName = "1.0"

    const val minSdk = 30
    const val targetSdk = 33
    const val compileSdk = 33

    const val androidXCoreKtx = "1.9.0"
    const val kotlin = "1.8.0"
    const val coroutines = "1.6.4"
    const val hilt = "2.44"
}

object Dependencies {

    object Android {
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidXCoreKtx}"
    }

    object Wear {

    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val flowTurbine = "app.cash.turbine:turbine:0.8.0"
    }

    object Hilt {

    }
}

//object Versions {
//    const val versionCode = 1
//    const val versionName = "1.0"
//
//    const val minSdk = 21
//    const val targetSdk = 33
//    const val compileSdk = 33
//
//    const val kotlin = "1.8.0"
//    const val coroutines = "1.6.4"
//    const val kotlinXSerialization = "1.8.10"
//    const val androidXLifecycle = "2.5.1"
//    const val androidxTest = "1.2.0"
//    const val androidxTestExt = "1.1.1"
//    const val androidGradlePlugin = "7.4.1"
//    const val buildKonfig = "0.13.3"
//    const val junit = "4.13"
//    const val ktor = "2.2.3"
//    const val multiplatformSettings = "1.0.0"
//    const val koin = "3.2.0"
//    const val kspPlugin = "1.8.0-1.0.9"
//    const val androidxWork = "2.8.0"
//    const val jetpackCompose = "1.3.3"
//    const val kotlinxDateTime = "0.4.0"
//    const val coil = "2.2.2"
//    const val accompanistSystemUiColor = "0.28.0"
//}

//object Deps {
//
//    const val junit = "junit:junit:${Versions.junit}"
//    const val mockative = "io.mockative:mockative:1.2.3"
//    const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"
//    const val systemUiColor =
//        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanistSystemUiColor}"
//
//    object Android {
//        const val material = "com.google.android.material:material:1.1.0"
//        const val core = "androidx.core:core-ktx:1.2.0"
//        const val constraintlayout = "androidx.constraintlayout:constraintlayout:1.1.3"
//        const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
//        const val work = "androidx.work:work-runtime-ktx:${Versions.androidxWork}"
//        const val lifecycleViewModel =
//            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidXLifecycle}"
//    }
//
//    object AndroidXTest {
//        const val core = "androidx.test:core:${Versions.androidxTest}"
//        const val junit = "androidx.test.ext:junit:${Versions.androidxTestExt}"
//        const val runner = "androidx.test:runner:${Versions.androidxTest}"
//        const val rules = "androidx.test:rules:${Versions.androidxTest}"
//    }
//
//    object Koin {
//        const val core = "io.insert-koin:koin-core:${Versions.koin}"
//        const val android = "io.insert-koin:koin-android:${Versions.koin}"
//        const val test = "io.insert-koin:koin-test:${Versions.koin}"
//        const val junit = "io.insert-koin:koin-test-junit4:${Versions.koin}"
//    }
//
//    object Coroutines {
//        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
//        const val android =
//            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
//        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
//        const val flowTurbine = "app.cash.turbine:turbine:0.8.0"
//    }
//
//    object Ktor {
//        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
//        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
//        const val kotlinXSerialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
//        const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
//        const val ios = "io.ktor:ktor-client-darwin:${Versions.ktor}"
//        const val commonLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
//        const val androidLogging = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
//        const val commonMock = "io.ktor:ktor-client-mock:${Versions.ktor}"
//        const val androidMock = "io.ktor:ktor-client-mock-jvm:${Versions.ktor}"
//        const val iosMock = "io.ktor:ktor-client-mock-native:${Versions.ktor}"
//    }
//
//    object JetpackCompose {
//        const val ui = "androidx.compose.ui:ui:${Versions.jetpackCompose}"
//        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.jetpackCompose}"
//        const val toolingPreview =
//            "androidx.compose.ui:ui-tooling-preview:${Versions.jetpackCompose}"
//        const val foundation = "androidx.compose.foundation:foundation:1.3.1"
//        const val material = "androidx.compose.material:material:1.3.1"
//        const val activity = "androidx.activity:activity-compose:1.6.1"
//    }
//
//    object Coil {
//        const val core = "io.coil-kt:coil:${Versions.coil}"
//        const val compose = "io.coil-kt:coil-compose:${Versions.coil}"
//    }
//
//    object MultiplatformSettings {
//        const val multiplatformSettings =
//            "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
//        const val multiplatformSettingsTest =
//            "com.russhwolf:multiplatform-settings-test:${Versions.multiplatformSettings}"
//    }
//}