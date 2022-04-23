package com.tlb.testleboncoin

import android.app.Application
import com.tlb.testleboncoin.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

@Suppress("unused")
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            fragmentFactory()
            androidContext(this@MainApplication)
            modules(
                dataSourceModule,
                fragmentModule,
                interactorModule,
                repositoryModule,
                retrofitModule,
                roomModule,
                viewModelModule
            )
        }
    }
}