package com.tlb.core.data

import com.tlb.core.domain.Result
import com.tlb.core.domain.Title
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldHaveSize

import org.junit.Test
import java.lang.Exception
import kotlin.math.exp

class TitleRepositoryTest {
    private val remoteSource: TitleDataSource = mockk()
    private val localSource: TitleDataSource = mockk(relaxUnitFun = true)

    private val titleRepository = TitleRepository(remoteSource, localSource)

    private val title: Title = mockk()
    private val exception: Exception = mockk()

    @Test
    fun `remoteSource returns an empty list`(): Unit = runBlocking {
        val expected = listOf<Title>()
        coEvery { remoteSource.getTitles() } returns expected

        val result = titleRepository.getTitles()
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data shouldHaveSize expected.size
    }

    @Test
    fun `remoteSource returns a non empty list`(): Unit = runBlocking {
        val expected = listOf(title)
        coEvery { remoteSource.getTitles() } returns expected

        val result = titleRepository.getTitles()
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data shouldHaveSize expected.size
        result.data.first() shouldBe title
    }

    @Test
    fun `remoteSource fails and localSource returns an empty list`(): Unit = runBlocking {
        val expected = listOf<Title>()
        coEvery { remoteSource.getTitles() } throws exception
        coEvery { localSource.getTitles() } returns listOf()

        val result = titleRepository.getTitles()
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data shouldHaveSize expected.size
    }

    @Test
    fun `remoteSource fails and localSource returns a non empty list`(): Unit = runBlocking {
        val expected = listOf(title)
        coEvery { remoteSource.getTitles() } throws exception
        coEvery { localSource.getTitles() } returns expected

        val result = titleRepository.getTitles()
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data shouldHaveSize expected.size
        result.data.first() shouldBe title
    }

    @Test
    fun `remoteSource and localSource both fails`(): Unit = runBlocking {
        coEvery { remoteSource.getTitles() } throws exception
        coEvery { localSource.getTitles() } throws exception

        val result = titleRepository.getTitles()
        result shouldBeInstanceOf Result.Error::class.java
        (result as Result.Error).throwable shouldBe exception
    }
}