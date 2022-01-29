package com.example.core.data.source.remote.response.business

import com.google.gson.annotations.SerializedName

data class BusinessCoordinatesResponse (

    @field:SerializedName("latitude")
    val latitude: Double,

    @field:SerializedName("longitude")
    val longitude: Double,

    )