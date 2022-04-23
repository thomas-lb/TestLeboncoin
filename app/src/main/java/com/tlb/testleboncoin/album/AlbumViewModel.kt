package com.tlb.testleboncoin.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tlb.core.domain.Album
import com.tlb.core.domain.Result
import com.tlb.core.interactors.GetAlbum
import com.tlb.testleboncoin.base.BaseViewModel
import kotlinx.coroutines.launch

class AlbumViewModel(
    private val getAlbum: GetAlbum
) : BaseViewModel() {
    var id = 0
        set(value) {
            field = value
            fetchData(value)
        }

    private val _albumData = MutableLiveData<Album>()
    val albumData = _albumData as LiveData<Album>

    fun fetchData(id: Int) = viewModelScope.launch {
        _loadingData.value = true
        when (val result = getAlbum(id)) {
            is Result.Success -> result.data.let(_albumData::setValue)
            is Result.Error -> _errorData.value = result
        }
        _loadingData.value = false
    }
}