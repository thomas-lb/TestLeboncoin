package com.tlb.testleboncoin.di

import com.tlb.testleboncoin.album.AlbumFragment
import com.tlb.testleboncoin.albums.AlbumsFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val fragmentModule = module {
    fragment { AlbumFragment(get()) }
    fragment { AlbumsFragment(get()) }
}