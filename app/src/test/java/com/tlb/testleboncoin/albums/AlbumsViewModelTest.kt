package com.tlb.testleboncoin.albums

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result
import com.tlb.core.interactors.AlbumList
import com.tlb.core.interactors.GetAlbums
import com.tlb.testleboncoin.CoroutinesTestRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class AlbumsViewModelTest {

    @get:Rule
    val testCoroutineRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getAlbums: GetAlbums = mockk()

    private val albumList: AlbumList = mockk()
    private val successResult: Result.Success<AlbumList> = mockk()
    private val notFoundResult: Result.Error.NotFound<AlbumList> = mockk()
    private val unknownResult: Result.Error.Unknown<AlbumList> = mockk()
    private val albumsObserver: Observer<AlbumList> = mockk()
    private val errorObserver: Observer<Result.Error<*>> = mockk()

    @Before
    fun setUp() {
        every { successResult.data } returns albumList

        every { albumsObserver.onChanged(any()) } just runs
        every { errorObserver.onChanged(any()) } just runs
    }

    @Test
    fun `Check that fetch data has been called at init`() {
        coEvery { getAlbums() } returns successResult

        val albumsViewModel = getFreshViewModel()

        albumsViewModel.albumsData.value shouldBeEqualTo albumList
        albumsViewModel.errorData.value shouldBeEqualTo null

        removeObservers(albumsViewModel)
    }

    @Test
    fun `Fetch albums with success`() = testCoroutineRule.testDispatcher.runBlockingTest {
        coEvery { getAlbums() } returns successResult

        val albumsViewModel = getFreshViewModel()
        albumsViewModel.fetchData()

        albumsViewModel.albumsData.value shouldBeEqualTo albumList
        albumsViewModel.errorData.value shouldBeEqualTo null

        removeObservers(albumsViewModel)
    }

    @Test
    fun `Try to fetch albums but get NotFound error`() = testCoroutineRule.testDispatcher.runBlockingTest {
        coEvery { getAlbums() } returns notFoundResult

        val albumsViewModel = getFreshViewModel()
        albumsViewModel.fetchData()

        albumsViewModel.albumsData.value shouldBeEqualTo null
        albumsViewModel.errorData.value shouldBeEqualTo notFoundResult

        removeObservers(albumsViewModel)
    }

    @Test
    fun `Try to fetch albums but get Unknown error`() = testCoroutineRule.testDispatcher.runBlockingTest {
        coEvery { getAlbums() } returns unknownResult

        val albumsViewModel = getFreshViewModel()
        albumsViewModel.fetchData()

        albumsViewModel.albumsData.value shouldBeEqualTo null
        albumsViewModel.errorData.value shouldBeEqualTo unknownResult

        removeObservers(albumsViewModel)
    }

    private fun getFreshViewModel() = AlbumsViewModel(getAlbums).apply {
        albumsData.observeForever(albumsObserver)
        errorData.observeForever(errorObserver)
    }

    private fun removeObservers(albumsViewModel: AlbumsViewModel) {
        albumsViewModel.albumsData.removeObserver(albumsObserver)
        albumsViewModel.errorData.removeObserver(errorObserver)
    }
}