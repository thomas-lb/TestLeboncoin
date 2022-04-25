object Deps {
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val liveData by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.lifecycle}" }
    val viewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}" }
    val recyclerView by lazy { "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerView}" }
    val koinCore by lazy { "io.insert-koin:koin-core:${Versions.koin}" }
    val koinAndroid by lazy { "io.insert-koin:koin-android:${Versions.koin}" }
    val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}" }
    val navigationUI by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val converterGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.AndroidX.room}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.AndroidX.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.AndroidX.room}" }
    val coil by lazy { "io.coil-kt:coil:${Versions.coil}" }
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KotlinX.coroutines}" }

    object Test {
        val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
        val kluent by lazy { "org.amshove.kluent:kluent-android:${Versions.kluent}" }
        val junit by lazy { "junit:junit:${Versions.junit}" }
        val coreTesting by lazy { "androidx.arch.core:core-testing:${Versions.AndroidX.coreTesting}" }
        val coroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KotlinX.coroutines}" }

    }

    object AndroidTest {
        val junit by lazy { "androidx.test.ext:junit:${Versions.AndroidX.junit}" }
        val kaspresso by lazy { "com.kaspersky.android-components:kaspresso:${Versions.kaspresso}" }
    }
}