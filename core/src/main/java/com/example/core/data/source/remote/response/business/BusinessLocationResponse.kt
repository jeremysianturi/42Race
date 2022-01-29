package com.example.core.data.source.remote.response.business

import com.google.gson.annotations.SerializedName

data class BusinessLocationResponse (

    @field:SerializedName("address1")
    val address1: String?,

    @field:SerializedName("address2")
    val address2: String?,

    @field:SerializedName("address3")
    val address3: String?,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("zip_code")
    val zipCode: String,

    @field:SerializedName("country")
    val country: String,

    @field:SerializedName("state")
    val state: String,

    @field:SerializedName("display_address")
    val displayAddress: List<String>,


    )