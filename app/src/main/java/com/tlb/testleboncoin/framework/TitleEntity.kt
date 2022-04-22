package com.tlb.testleboncoin.framework

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tlb.core.domain.Title

@Entity(tableName = "TITLES")
data class TitleEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val albumId: Int,
    val url: String,
    val thumbnailUrl: String
)