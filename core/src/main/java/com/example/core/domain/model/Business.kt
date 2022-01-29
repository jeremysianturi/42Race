package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Business(
    val id: String,
    val alias: String,
    val name: String,
    val imageUrl: String,
    val isClosed: String,
    val url: String,
    val reviewCount: Int,

    // categories
    val categoriesAlias: String,
    val categoriesTitle: String,

    val rating: Double,

    // coordinates
    val coordinatesLatitude: Double,
    val coordinatesLongitude: Double,

    val transactions: String,
    val price: String?,

    // location
    val locationAddress1: String?,
    val locationAddress2: String?,
    val locationAddress3: String?,
    val locationCity: String,
    val locationZipCode: String,
    val locationCountry: String,
    val locationState: String,
    val locationDisplayAddress: String,


    val phone: String,
    val displayPhone: String,
    val distance: Double
) : Parcelable