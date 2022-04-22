package com.tlb.testleboncoin.di

import com.tlb.core.data.TitleRepository
import com.tlb.testleboncoin.di.DataSource.TITLE_LOCAL
import com.tlb.testleboncoin.di.DataSource.TITLE_REMOTE
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single {
        TitleRepository(
            get(named(TITLE_REMOTE)),
            get(named(TITLE_LOCAL))
        )
    }
}