package com.example.core.data.source.remote.response.business

import com.google.gson.annotations.SerializedName

data class BusinessCategoryResponse (

    @field:SerializedName("alias")
    val alias: String,

    @field:SerializedName("title")
    val title: String,

        )