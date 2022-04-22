package com.tlb.testleboncoin.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://static.leboncoin.fr/img/shared/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}