package com.tlb.core.data

import com.tlb.core.domain.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TitleRepository(
    private val remoteSource: TitleDataSource,
    private val localSource: TitleDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getTitles() = withContext(ioDispatcher) {
        try {
            val titles = remoteSource.getTitles()
            localSource.removeTitles()
            localSource.updateTitles(titles)
            Result.Success(titles)
        } catch (t: Throwable) {
            try {
                val titles = localSource.getTitles()
                if (titles.isNotEmpty()) {
                    Result.Success(titles)
                } else Result.Error.NotFound()
            } catch (t: Throwable) {
                Result.Error.Unknown()
            }
        }
    }
}