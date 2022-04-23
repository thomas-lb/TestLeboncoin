package com.tlb.testleboncoin.di

import androidx.room.Room
import com.tlb.testleboncoin.framework.db.AppDatabase
import com.tlb.testleboncoin.framework.db.TitleMapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "database"
        ).build()
    }

    single {
        get<AppDatabase>().titleDao()
    }

    single { TitleMapper() }
}