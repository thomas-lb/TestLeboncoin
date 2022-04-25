package com.tlb.core.extensions

import com.tlb.core.domain.Album
import com.tlb.core.domain.Result
import com.tlb.core.domain.Title
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeInstanceOf
import org.junit.Test

class ErrorExtensionsTest {

    @Test
    fun map() {
        Result.Error.NotFound<Title>()
            .map<Title, Album>() shouldBeInstanceOf Result.Error.NotFound::class.java
        Result.Error.Unknown<Title>()
            .map<Title, Album>() shouldBeInstanceOf Result.Error.Unknown::class.java

        Result.Error.NotFound<Title>()
            .map<Title, Album>() shouldNotBeInstanceOf Result.Error.Unknown::class.java
        Result.Error.Unknown<Title>()
            .map<Title, Album>() shouldNotBeInstanceOf Result.Error.NotFound::class.java
    }
}