package com.tlb.core.interactors

import com.tlb.core.data.TitleRepository
import com.tlb.core.domain.Result
import com.tlb.core.domain.Title
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldHaveSize
import org.junit.Test

class GetAlbumsTest {

    private val titleRepository: TitleRepository = mockk()
    private val getAlbums = GetAlbums(titleRepository)


    @Test
    fun `titleRepository returns an empty list`(): Unit = runBlocking {
        val expected = AlbumList()
        coEvery { titleRepository.getTitles() } returns Result.Success(listOf())
        getAlbums() shouldBeInstanceOf Result.Success::class.java
        getAlbums() shouldBeEqualTo Result.Success(expected)
    }

    @Test
    fun `titleRepository returns a non empty list with 2 different albums`(): Unit = runBlocking {
        val titles = listOf(
            Title(1, "title", 1, "url", "thumbnailUrl"),
            Title(2, "title", 1, "url", "thumbnailUrl"),
            Title(3, "title", 2, "url", "thumbnailUrl"),
            Title(4, "title", 2, "url", "thumbnailUrl"),
            Title(5, "title", 2, "url", "thumbnailUrl"),
        )
        coEvery { titleRepository.getTitles() } returns Result.Success(titles)

        val result = getAlbums()
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data.albums shouldHaveSize 2
        result.data.favorites shouldHaveSize 0
        result.data.recommended shouldHaveSize 0
    }

    @Test
    fun `titleRepository returns a non empty list with 5 different albums`(): Unit = runBlocking {
        val titles = listOf(
            Title(1, "title", 1, "url", "thumbnailUrl"),
            Title(2, "title", 1, "url", "thumbnailUrl"),
            Title(3, "title", 2, "url", "thumbnailUrl"),
            Title(4, "title", 2, "url", "thumbnailUrl"),
            Title(5, "title", 3, "url", "thumbnailUrl"),
            Title(5, "title", 4, "url", "thumbnailUrl"),
            Title(5, "title", 5, "url", "thumbnailUrl"),
            Title(5, "title", 5, "url", "thumbnailUrl"),
            Title(5, "title", 5, "url", "thumbnailUrl"),
        )
        coEvery { titleRepository.getTitles() } returns Result.Success(titles)

        val result = getAlbums()
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data.albums shouldHaveSize 5
        result.data.favorites shouldHaveSize 3
        result.data.recommended shouldHaveSize 0
    }

    @Test
    fun `titleRepository returns a non empty list with 8 different albums`(): Unit = runBlocking {
        val titles = listOf(
            Title(1, "title", 1, "url", "thumbnailUrl"),
            Title(2, "title", 1, "url", "thumbnailUrl"),
            Title(3, "title", 2, "url", "thumbnailUrl"),
            Title(4, "title", 2, "url", "thumbnailUrl"),
            Title(5, "title", 3, "url", "thumbnailUrl"),
            Title(5, "title", 4, "url", "thumbnailUrl"),
            Title(5, "title", 5, "url", "thumbnailUrl"),
            Title(5, "title", 5, "url", "thumbnailUrl"),
            Title(5, "title", 5, "url", "thumbnailUrl"),
            Title(5, "title", 6, "url", "thumbnailUrl"),
            Title(5, "title", 6, "url", "thumbnailUrl"),
            Title(5, "title", 6, "url", "thumbnailUrl"),
            Title(5, "title", 7, "url", "thumbnailUrl"),
            Title(5, "title", 7, "url", "thumbnailUrl"),
            Title(5, "title", 8, "url", "thumbnailUrl"),
        )
        coEvery { titleRepository.getTitles() } returns Result.Success(titles)

        val result = getAlbums()
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data.albums shouldHaveSize 8
        result.data.favorites shouldHaveSize 3
        result.data.recommended shouldHaveSize 3
    }

    @Test
    fun `titleRepository fails with NotFound`(): Unit = runBlocking {
        coEvery { titleRepository.getTitles() } returns Result.Error.NotFound()

        val result = getAlbums()
        result shouldBeInstanceOf Result.Error.NotFound::class.java
    }

    @Test
    fun `titleRepository fails with Unknown`(): Unit = runBlocking {
        coEvery { titleRepository.getTitles() } returns Result.Error.Unknown()

        val result = getAlbums()
        result shouldBeInstanceOf Result.Error.Unknown::class.java
    }

}