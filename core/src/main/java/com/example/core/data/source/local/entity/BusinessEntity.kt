package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "businesses")
data class BusinessEntity(

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "alias")
    val alias: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "is_closed")
    val isClosed: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "reviewCount")
    val reviewCount: Int,


    // categories
    @ColumnInfo(name = "categories_alias")
    val categoriesAlias: String,

    @ColumnInfo(name = "categories_title")
    val categoriesTitle: String,
    // categories

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "coordinates_latitude")
    val coordinatesLatitude: Double,

    @ColumnInfo(name = "coordinates_longitude")
    val coordinatesLongitude: Double,

    @ColumnInfo(name = "transactions")
    val transactions: String,

    @ColumnInfo(name = "price")
    val price: String?,

    // location
    @ColumnInfo(name = "location_address1")
    val locationAddress1: String?,

    @ColumnInfo(name = "location_address2")
    val locationAddress2: String?,

    @ColumnInfo(name = "location_address3")
    val locationAddress3: String?,

    @ColumnInfo(name = "location_city")
    val locationCity: String,

    @ColumnInfo(name = "location_zip_code")
    val locationZipCode: String,

    @ColumnInfo(name = "location_country")
    val locationCountry: String,

    @ColumnInfo(name = "location_state")
    val locationState: String,

    @ColumnInfo(name = "location_display_address")
    val locationDisplayAddress: String,
    // location

    @ColumnInfo(name = "phone")
    val phone: String,

    @ColumnInfo(name = "display_phone")
    val displayPhone: String,

    @ColumnInfo(name = "distance")
    val distance: Double,

)