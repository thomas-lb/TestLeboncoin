package com.tlb.testleboncoin.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result
import com.tlb.core.interactors.AlbumList
import com.tlb.core.interactors.GetAlbums
import com.tlb.testleboncoin.base.BaseViewModel
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val getAlbums: GetAlbums
): BaseViewModel() {
    private val _albumsData = MutableLiveData<AlbumList>()
    val albumsData = _albumsData as LiveData<AlbumList>

    init {
        fetchData()
    }

    fun fetchData() = viewModelScope.launch {
        _loadingData.postValue(true)
        val result = getAlbums()
        when(result) {
            is Result.Success -> _albumsData.postValue(result.data)
            is Result.Error -> _errorData.postValue(result)
        }
        _loadingData.postValue(false)
    }
}