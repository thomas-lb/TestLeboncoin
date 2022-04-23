package com.tlb.testleboncoin.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TitleDao {
    @Query("SELECT * FROM TITLES")
    suspend fun getTitles(): List<TitleEntity>

    @Insert
    suspend fun insertTitles(titles: List<TitleEntity>)

    @Query("DELETE FROM TITLES")
    suspend fun clearTitles()
}