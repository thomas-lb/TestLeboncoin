package com.tlb.core.domain

data class Album(
    val id: Int,
    val url: String,
    val thumbnailUrl: String,
    val titles: List<Title>
)