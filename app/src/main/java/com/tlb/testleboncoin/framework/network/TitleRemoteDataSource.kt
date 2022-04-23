package com.tlb.testleboncoin.framework.network

import com.tlb.core.data.TitleDataSource
import com.tlb.core.domain.Title
import retrofit2.http.GET

interface TitleRemoteDataSource : TitleDataSource {
    @GET("technical-test.json")
    override suspend fun getTitles(): List<Title>
    override suspend fun updateTitles(titles: List<Title>) {
    }

    override suspend fun removeTitles() {
    }
}