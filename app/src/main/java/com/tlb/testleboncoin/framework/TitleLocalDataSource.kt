package com.tlb.testleboncoin.framework

import com.tlb.core.data.TitleDataSource
import com.tlb.core.domain.Title

class TitleLocalDataSource(
    private val titleDao: TitleDao,
    private val titleMapper: TitleMapper
): TitleDataSource {
    override suspend fun getTitles() = titleDao.getTitles().map { titleMapper.toTitle(it) }

    override suspend fun updateTitles(titles: List<Title>) {
        titleDao.insertTitles(titles.map { titleMapper.toEntity(it) })
    }

    override suspend fun removeTitles() {
        titleDao.clearTitles()
    }
}