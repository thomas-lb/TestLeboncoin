package com.tlb.testleboncoin.di

import com.tlb.testleboncoin.album.AlbumViewModel
import com.tlb.testleboncoin.albums.AlbumsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AlbumViewModel(get()) }
    viewModel { AlbumsViewModel(get()) }
}