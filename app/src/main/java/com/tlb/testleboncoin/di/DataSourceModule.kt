package com.tlb.testleboncoin.di

import com.tlb.core.data.TitleDataSource
import com.tlb.testleboncoin.framework.TitleLocalDataSource
import com.tlb.testleboncoin.framework.TitleRemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object DataSource {
    const val TITLE_LOCAL = "TITLE_LOCAL"
    const val TITLE_REMOTE = "TITLE_REMOTE"
}

val dataSourceModule = module {
    single<TitleDataSource>(named(DataSource.TITLE_LOCAL)) {
        TitleLocalDataSource(get(), get())
    }
    single<TitleDataSource>(named(DataSource.TITLE_REMOTE)) {
        get<Retrofit>().create(TitleRemoteDataSource::class.java)
    }
}
