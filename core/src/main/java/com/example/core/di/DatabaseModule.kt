package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.source.local.room.BusinessesDatabase
import com.example.core.data.source.local.room.dao.BusinessDao
import com.example.core.data.source.local.room.dao.BusinessReviewDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BusinessesDatabase = Room.databaseBuilder(
        context,
        BusinessesDatabase::class.java, "Businesses.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideBusinessesDao(database: BusinessesDatabase): BusinessDao = database.businessesDao()

    @Provides
    fun provideBusinessReviewDao(database: BusinessesDatabase): BusinessReviewDao = database.businessReviewDao()

}