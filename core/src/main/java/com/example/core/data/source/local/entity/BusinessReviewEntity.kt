package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "business_review")
data class BusinessReviewEntity(

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "time_created")
    val timeCreated: String,

    @ColumnInfo(name = "user_id")
    val userId: String,

    @ColumnInfo(name = "user_profile_url")
    val userProfileUrl: String,

    @ColumnInfo(name = "user_image_url")
    val userImageUrl: String,

    @ColumnInfo(name = "user_name")
    val userName: String,

    )