package com.tlb.testleboncoin.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result
import com.tlb.core.interactors.GetAlbums
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val getAlbums: GetAlbums
): ViewModel() {
    private val _albumsData = MutableLiveData<List<Album>>()
    val albumsData = _albumsData as LiveData<List<Album>>

    private val _errorData = MutableLiveData<Result.Error<List<Album>>>()
    val errorData = _errorData as LiveData<Result.Error<List<Album>>>

    init {
        fetchData()
    }

    fun fetchData() = viewModelScope.launch {
        val result = getAlbums()
        when(result) {
            is Result.Success -> _albumsData.postValue(result.data)
            is Result.Error -> _errorData.postValue(result)
        }
    }
}