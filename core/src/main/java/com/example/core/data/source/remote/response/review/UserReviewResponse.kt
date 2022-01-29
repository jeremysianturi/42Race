package com.example.core.data.source.remote.response.review

import com.google.gson.annotations.SerializedName

data class UserReviewResponse (

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("profile_url")
    val profileUrl: String,

    @field:SerializedName("image_url")
    val imageUrl: String?,

    @field:SerializedName("name")
    val name: String,

    )