package com.tlb.testleboncoin.framework

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TitleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun titleDao(): TitleDao
}