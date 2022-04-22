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

class GetAlbumTest {

    private val titleRepository: TitleRepository = mockk()
    private val getAlbum = GetAlbum(titleRepository)

    private val titles = listOf(
        Title(1, "title", 1, "url", "thumbnailUrl"),
        Title(2, "title", 1, "url", "thumbnailUrl"),
        Title(3, "title", 2, "url", "thumbnailUrl"),
        Title(4, "title", 2, "url", "thumbnailUrl"),
        Title(5, "title", 2, "url", "thumbnailUrl"),
    )

    @Test
    fun `titleRepository returns an empty list`(): Unit = runBlocking {
        val expected = listOf<Title>()
        coEvery { titleRepository.getTitles() } returns Result.Success(expected)
        getAlbum(0) shouldBeInstanceOf Result.Error.NotFound::class.java
    }

    @Test
    fun `titleRepository returns a non empty list and album not existing`(): Unit = runBlocking {
        coEvery { titleRepository.getTitles() } returns Result.Success(titles)
        getAlbum(0) shouldBeInstanceOf Result.Error.NotFound::class.java
    }

    @Test
    fun `titleRepository returns a non empty list and an album is found`(): Unit = runBlocking {
        coEvery { titleRepository.getTitles() } returns Result.Success(titles)
        val result = getAlbum(1)
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data.id shouldBeEqualTo 1
        result.data.titles shouldHaveSize 2
    }

    @Test
    fun `titleRepository fails with NotFound`(): Unit = runBlocking {
        coEvery { titleRepository.getTitles() } returns Result.Error.NotFound()

        val result = getAlbum(0)
        result shouldBeInstanceOf Result.Error.NotFound::class.java
    }

    @Test
    fun `titleRepository fails with Unknown`(): Unit = runBlocking {
        coEvery { titleRepository.getTitles() } returns Result.Error.Unknown()

        val result = getAlbum(0)
        result shouldBeInstanceOf Result.Error.Unknown::class.java
    }
}