package com.tlb.core.interactors

import com.tlb.core.data.TitleRepository
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result
import com.tlb.core.extensions.map

class GetAlbums(private val titleRepository: TitleRepository) {
    suspend operator fun invoke() = when (val result = titleRepository.getTitles()) {
        is Result.Success -> {
            val albums = result.data.groupBy { it.albumId }
                .map { (albumId, titles) ->
                    val first = titles.firstOrNull() ?: return@map null
                    Album(
                        albumId,
                        first.url,
                        first.thumbnailUrl,
                        titles
                    )
                }.filterNotNull()

            Result.Success(
                AlbumList(
                    favorites = if (albums.size > 3) albums.subList(0, 3) else listOf(),
                    recommended = if (albums.size > 6) albums.subList(3, 6) else listOf(),
                    albums = albums
                )
            )
        }
        is Result.Error -> result.map()
    }
}

data class AlbumList(
    val favorites: List<Album> = listOf(),
    val recommended: List<Album> = listOf(),
    val albums: List<Album> = listOf()
)