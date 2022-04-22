package com.tlb.testleboncoin.framework

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TITLES")
data class TitleEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val albumId: Int,
    val url: String,
    val thumbnailUrl: String
)