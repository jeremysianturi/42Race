package com.example.core.data.source.remote.response.review

import com.google.gson.annotations.SerializedName

data class ReviewResponse (

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("text")
    val text: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("time_created")
    val timeCreated: String,

    @field:SerializedName("user")
    val user: UserReviewResponse

    )