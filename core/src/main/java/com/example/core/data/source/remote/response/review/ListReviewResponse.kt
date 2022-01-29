package com.example.core.data.source.remote.response.review

import com.example.core.data.source.remote.response.business.BusinessResponse
import com.google.gson.annotations.SerializedName

data class ListReviewResponse(

    @field:SerializedName("reviews")
    val data: List<ReviewResponse>,

    @field:SerializedName("total")
    val total: Int
)