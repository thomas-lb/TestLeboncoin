package com.tlb.core.interactors

import com.tlb.core.data.TitleRepository
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result
import com.tlb.core.domain.Title
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldHaveSize
import org.junit.Assert.*

import org.junit.Test
import java.lang.Exception

class GetAlbumsTest {

    private val titleRepository: TitleRepository = mockk()
    private val getAlbums = GetAlbums(titleRepository)

    private val titles = listOf(
        Title(1, "title", 1, "url", "thumbnailUrl"),
        Title(2, "title", 1, "url", "thumbnailUrl"),
        Title(3, "title", 2, "url", "thumbnailUrl"),
        Title(4, "title", 2, "url", "thumbnailUrl"),
        Title(5, "title", 2, "url", "thumbnailUrl"),
    )
    private val exception: Exception = mockk()

    @Test
    fun `titleRepository returns an empty list`(): Unit = runBlocking {
        val expected = AlbumList()
        coEvery { titleRepository.getTitles() } returns Result.Success(listOf())
        getAlbums() shouldBeInstanceOf Result.Success::class.java
        getAlbums() shouldBeEqualTo Result.Success(expected)
    }

    @Test
    fun `titleRepository returns a non empty list`(): Unit = runBlocking {
        coEvery { titleRepository.getTitles() } returns Result.Success(titles)

        val result = getAlbums()
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data.albums shouldHaveSize 2
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