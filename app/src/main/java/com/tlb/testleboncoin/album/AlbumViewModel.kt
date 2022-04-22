package com.tlb.testleboncoin.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result
import com.tlb.core.interactors.GetAlbum
import kotlinx.coroutines.launch

class AlbumViewModel(
    private val getAlbum: GetAlbum
): ViewModel() {
    var id = 0
        set(value) {
            field = value
            fetchData(value)
        }

    private val _albumData = MutableLiveData<Album>()
    val albumData = _albumData as LiveData<Album>

    private val _errorData = MutableLiveData<Result.Error<Album>>()
    val errorData = _errorData as LiveData<Result.Error<Album>>

    fun fetchData(id: Int) = viewModelScope.launch {
        when(val result = getAlbum(id)) {
            is Result.Success -> _albumData.postValue(result.data)
            is Result.Error -> _errorData.postValue(result)
        }
    }
}