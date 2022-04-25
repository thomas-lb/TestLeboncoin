package com.tlb.core.extensions

import com.tlb.core.domain.Result

fun <T, R> Result.Error<T>.map(): Result.Error<R> = when (this) {
    is Result.Error.NotFound -> Result.Error.NotFound()
    is Result.Error.Unknown -> Result.Error.Unknown()
}