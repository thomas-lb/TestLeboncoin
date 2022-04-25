plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdkVersion(ConfigData.sdkVersion)

    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(ConfigData.sdkVersion)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(Deps.coreKtx)
    api(Deps.coroutinesCore)

    testImplementation(Deps.Test.junit)
    testImplementation(Deps.Test.mockk)
    testImplementation(Deps.Test.kluent)
    testImplementation(Deps.Test.coroutinesTest)

    androidTestImplementation(Deps.AndroidTest.junit)
}