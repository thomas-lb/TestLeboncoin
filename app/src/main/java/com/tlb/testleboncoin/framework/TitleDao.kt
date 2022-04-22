package com.tlb.testleboncoin.framework

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tlb.core.data.TitleDataSource
import com.tlb.core.domain.Title

@Dao
interface TitleDao {
    @Query("SELECT * FROM TITLES")
    suspend fun getTitles(): List<TitleEntity>

    @Insert
    suspend fun insertTitles(titles: List<TitleEntity>)

    @Query("DELETE FROM TITLES")
    suspend fun clearTitles()
}