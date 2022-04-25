plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(ConfigData.sdkVersion)

    defaultConfig {
        applicationId = "com.tlb.testleboncoin"
        minSdkVersion(ConfigData.minSdkVersion)
        targetSdkVersion(ConfigData.sdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }
    buildTypes {
        getByName("release") {
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
    implementation(project(":core"))
    implementation(Deps.coreKtx)
    implementation(Deps.material)
    implementation(Deps.liveData)
    implementation(Deps.viewModel)
    implementation(Deps.recyclerView)
    implementation(Deps.koinCore)
    implementation(Deps.koinAndroid)
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUI)
    implementation(Deps.retrofit)
    implementation(Deps.converterGson)
    implementation(Deps.roomRuntime)
    implementation(Deps.roomKtx)
    kapt(Deps.roomCompiler)
    implementation(Deps.coil)

    testImplementation(Deps.Test.mockk)
    testImplementation(Deps.Test.kluent)
    testImplementation(Deps.Test.junit)
    testImplementation(Deps.Test.coreTesting)
    testImplementation(Deps.Test.coroutinesTest)

    androidTestImplementation(Deps.AndroidTest.junit)
    androidTestImplementation(Deps.AndroidTest.kaspresso)
}