package com.example.core.data.source.remote.response.business

import com.google.gson.annotations.SerializedName

data class BusinessResponse (

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("alias")
    val alias: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("is_closed")
    val isClosed: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("review_count")
    val reviewCount: Int,

    @field:SerializedName("categories")
    val categories: List<BusinessCategoryResponse>,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("coordinates")
    val coordinates: BusinessCoordinatesResponse,

    @field:SerializedName("transactions")
    val transactions: List<String>,

    @field:SerializedName("price")
    val price: String?,

    @field:SerializedName("location")
    val location: BusinessLocationResponse,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("display_phone")
    val displayPhone: String,

    @field:SerializedName("distance")
    val distance: Double,

    )

