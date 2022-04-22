package com.tlb.core.interactors

import com.tlb.core.data.TitleRepository
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result

class GetAlbums(private val titleRepository: TitleRepository) {
    suspend operator fun invoke() = when(val result = titleRepository.getTitles()) {
        is Result.Success -> Result.Success(
            result.data.groupBy { it.albumId }
                .map { (albumId, titles) ->
                    val first = titles.firstOrNull() ?: return@map null
                    Album(
                        albumId,
                        first.url,
                        first.thumbnailUrl,
                        titles
                    )
                }.filterNotNull()
        )
        is Result.Error -> Result.Error(result.throwable)
    }
}