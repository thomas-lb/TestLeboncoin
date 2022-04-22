package com.tlb.core.data

import com.tlb.core.domain.Result
import com.tlb.core.domain.Title

class TitleRepository(
    private val remoteSource: TitleDataSource,
    private val localSource: TitleDataSource
) {

    suspend fun getTitles(): Result<List<Title>> {
        return try {
            val titles = remoteSource.getTitles()
            localSource.removeTitles()
            localSource.updateTitles(titles)
            Result.Success(titles)
        } catch (t: Throwable) {
            try {
                Result.Success(localSource.getTitles())
            } catch (t: Throwable) {
                Result.Error(t)
            }
        }
    }
}