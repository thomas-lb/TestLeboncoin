package com.tlb.core.data

import com.tlb.core.domain.Title

interface TitleDataSource {
    suspend fun getTitles(): List<Title>
    suspend fun updateTitles(titles: List<Title>)
    suspend fun removeTitles()
}