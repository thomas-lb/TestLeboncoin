package com.tlb.testleboncoin.album

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result
import com.tlb.core.interactors.GetAlbum
import com.tlb.testleboncoin.CoroutinesTestRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AlbumViewModelTest {

    @get:Rule
    val testCoroutineRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    private val getAlbum: GetAlbum = mockk()
    private val successResult: Result.Success<Album> = mockk()
    private val notFoundResult: Result.Error.NotFound<Album> = mockk()
    private val unknownResult: Result.Error.Unknown<Album> = mockk()
    private val album: Album = mockk()
    private val albumObserver: Observer<Album> = mockk()
    private val errorObserver: Observer<Result.Error<*>> = mockk()

    private val albumViewModel = spyk(AlbumViewModel(getAlbum))

    @Before
    fun setUp() {
        every { successResult.data } returns album

        every { albumObserver.onChanged(any()) } just runs
        every { errorObserver.onChanged(any()) } just runs

        coEvery { getAlbum(SUCCESS_ALBUM_ID) } returns successResult
        coEvery { getAlbum(NOT_FOUND_ALBUM_ID) } returns notFoundResult
        coEvery { getAlbum(UNKNOWN_ALBUM_ID) } returns unknownResult

        albumViewModel.albumData.observeForever(albumObserver)
        albumViewModel.errorData.observeForever(errorObserver)
    }

    @After
    fun tearDown() {
        albumViewModel.albumData.removeObserver(albumObserver)
        albumViewModel.errorData.removeObserver(errorObserver)
    }

    @Test
    fun setId() {
        albumViewModel.id = SUCCESS_ALBUM_ID

        albumViewModel.id shouldBeEqualTo SUCCESS_ALBUM_ID
        verify { albumViewModel.fetchData(SUCCESS_ALBUM_ID) }
    }

    @Test
    fun `Fetch album with success`() = testCoroutineRule.testDispatcher.runBlockingTest {
        albumViewModel.fetchData(SUCCESS_ALBUM_ID)

        albumViewModel.albumData.value shouldBeEqualTo album
    }

    @Test
    fun `Try to fetch not found album`() = testCoroutineRule.testDispatcher.runBlockingTest {
        albumViewModel.fetchData(NOT_FOUND_ALBUM_ID)

        albumViewModel.errorData.value shouldBeEqualTo notFoundResult
    }

    @Test
    fun `Try to fetch unknown album`() = testCoroutineRule.testDispatcher.runBlockingTest {
        albumViewModel.fetchData(UNKNOWN_ALBUM_ID)

        albumViewModel.errorData.value shouldBeEqualTo unknownResult
    }

    companion object {
        private const val SUCCESS_ALBUM_ID = 0
        private const val NOT_FOUND_ALBUM_ID = 1
        private const val UNKNOWN_ALBUM_ID = 2
    }
}