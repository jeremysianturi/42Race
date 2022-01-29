package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BusinessReview(
    val id: String,
    val url: String,
    val text: String,
    val rating: Double,
    val timeCreated: String,
    val userId: String,
    val userProfileUrl: String,
    val userImageUrl: String,
    val userName: String
    ) : Parcelable