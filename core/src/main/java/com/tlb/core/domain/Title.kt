package com.tlb.core.domain

data class Title(
    val id: Int,
    val title: String,
    val albumId: Int,
    val url: String,
    val thumbnailUrl: String
)
