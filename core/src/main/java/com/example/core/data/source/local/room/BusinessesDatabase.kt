package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.data.source.local.entity.BusinessEntity
import com.example.core.data.source.local.entity.BusinessReviewEntity
import com.example.core.data.source.local.room.dao.BusinessDao
import com.example.core.data.source.local.room.dao.BusinessReviewDao
import retrofit2.Converter

@Database(
    entities = [
        BusinessEntity::class,
        BusinessReviewEntity::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class BusinessesDatabase : RoomDatabase() {

    abstract fun businessesDao(): BusinessDao

    abstract fun businessReviewDao(): BusinessReviewDao

}