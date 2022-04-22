package com.tlb.core.domain

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    sealed class Error<T> : Result<T>() {
        class NotFound<T> : Error<T>()
        class Unknown<T> : Error<T>()
    }
}