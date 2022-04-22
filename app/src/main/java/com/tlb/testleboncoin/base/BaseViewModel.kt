package com.tlb.testleboncoin.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tlb.core.domain.Result

abstract class BaseViewModel : ViewModel() {
    protected val _errorData = MutableLiveData<Result.Error<*>>()
    val errorData = _errorData as LiveData<Result.Error<*>>

    protected val _loadingData = MutableLiveData(false)
    val loadingData = _loadingData as LiveData<Boolean>
}