package com.tlb.core.data

import com.tlb.core.domain.Result
import com.tlb.core.domain.Title
import com.tlb.core.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldHaveSize
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class TitleRepositoryTest {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val remoteSource: TitleDataSource = mockk()
    private val localSource: TitleDataSource = mockk(relaxUnitFun = true)

    private val titleRepository = TitleRepository(
        remoteSource,
        localSource,
        StandardTestDispatcher(coroutinesTestRule.testDispatcher.scheduler)
    )

    private val title: Title = mockk()
    private val exception: Exception = mockk()

    @Test
    fun `remoteSource returns an empty list`(): Unit = runTest {
        val expected = listOf<Title>()
        coEvery { remoteSource.getTitles() } returns expected

        val result = titleRepository.getTitles()
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data shouldHaveSize expected.size
    }

    @Test
    fun `remoteSource returns a non empty list`() = runTest {
        val expected = listOf(title)
        coEvery { remoteSource.getTitles() } returns expected

        val result = titleRepository.getTitles()
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data shouldHaveSize expected.size
        result.data.first() shouldBe title
    }

    @Test
    fun `remoteSource fails and localSource returns an empty list`() = runTest {
        coEvery { remoteSource.getTitles() } throws exception
        coEvery { localSource.getTitles() } returns listOf()

        val result = titleRepository.getTitles()
        result shouldBeInstanceOf Result.Error.NotFound::class.java
    }

    @Test
    fun `remoteSource fails and localSource returns a non empty list`() = runTest {
        val expected = listOf(title)
        coEvery { remoteSource.getTitles() } throws exception
        coEvery { localSource.getTitles() } returns expected

        val result = titleRepository.getTitles()
        result shouldBeInstanceOf Result.Success::class.java
        (result as Result.Success).data shouldHaveSize expected.size
        result.data.first() shouldBe title
    }

    @Test
    fun `remoteSource and localSource both fails`() = runTest {
        coEvery { remoteSource.getTitles() } throws exception
        coEvery { localSource.getTitles() } throws exception

        val result = titleRepository.getTitles()
        result shouldBeInstanceOf Result.Error.Unknown::class.java
    }
}