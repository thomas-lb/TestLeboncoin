package com.tlb.core.domain

sealed class Result<T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error<T>(val throwable: Throwable): Result<T>()
}