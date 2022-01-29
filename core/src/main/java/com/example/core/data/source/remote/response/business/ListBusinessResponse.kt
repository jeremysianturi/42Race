package com.example.core.data.source.remote.response.business

import com.google.gson.annotations.SerializedName

data class ListBusinessResponse(

    @field:SerializedName("businesses")
    val data: List<BusinessResponse>,

    @field:SerializedName("total")
    val total: Int
)