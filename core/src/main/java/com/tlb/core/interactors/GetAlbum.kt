package com.tlb.core.interactors

import com.tlb.core.data.TitleRepository
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result

class GetAlbum(
    private val titleRepository: TitleRepository
) {
    suspend operator fun invoke(
        id: Int
    ) = when (val result = titleRepository.getTitles()) {
        is Result.Success -> {
            val entries = result.data
                .groupBy { it.albumId }
                .filterKeys { albumId -> albumId == id }
                .entries
            if (entries.isNotEmpty()) {
                val (albumId, titles) = entries.first()
                Result.Success(
                    Album(
                        albumId,
                        titles.first().url,
                        titles.first().thumbnailUrl,
                        titles,
                    )
                )
            } else {
                Result.Error.NotFound()
            }
        }
        is Result.Error.NotFound -> Result.Error.NotFound()
        is Result.Error.Unknown -> Result.Error.Unknown()
    }
}