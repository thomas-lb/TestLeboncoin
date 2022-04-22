package com.tlb.testleboncoin.di

import com.tlb.core.interactors.GetAlbum
import com.tlb.core.interactors.GetAlbums
import org.koin.dsl.module

val interactorModule = module {
    single { GetAlbum(get()) }
    single { GetAlbums(get()) }
}