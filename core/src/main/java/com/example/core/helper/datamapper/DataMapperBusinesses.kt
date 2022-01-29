package com.example.core.helper.datamapper

import com.example.core.data.source.local.entity.BusinessEntity
import com.example.core.data.source.remote.response.business.BusinessResponse
import com.example.core.domain.model.Business

object DataMapperBusinesses {

    fun mapResponsetoEntities(input: List<BusinessResponse>): List<BusinessEntity> {
        val businessList = ArrayList<BusinessEntity>()
        input.map {
            val business = BusinessEntity(
                id = it.id,
                alias = it.alias,
                name = it.name,
                imageUrl = it.imageUrl,
                isClosed = it.isClosed,
                url = it.url,
                reviewCount = it.reviewCount,
                categoriesAlias = it.categories[0].alias,
                categoriesTitle = it.categories[0].title,
                rating = it.rating,
                coordinatesLatitude = it.coordinates.latitude,
                coordinatesLongitude = it.coordinates.longitude,
                transactions = it.transactions.toString(),
                price = it.price,
                locationAddress1 = it.location.address1 ?: "",
                locationAddress2 = it.location.address2 ?: "",
                locationAddress3 = it.location.address3 ?: "",
                locationCity = it.location.city,
                locationZipCode = it.location.zipCode,
                locationCountry = it.location.country,
                locationState = it.location.state,
                locationDisplayAddress = it.location.displayAddress.toString(),
                phone = it.phone,
                displayPhone = it.displayPhone,
                distance = it.distance
            )
            businessList.add(business)
        }

        return businessList
    }

    fun mapEntitiestoDomain(input: List<BusinessEntity>): List<Business> =
        input.map {
            Business(
                id = it.id,
                alias = it.alias,
                name = it.name,
                imageUrl = it.imageUrl,
                isClosed = it.isClosed,
                url = it.url,
                reviewCount = it.reviewCount,
                categoriesAlias = it.categoriesAlias,
                categoriesTitle = it.categoriesTitle,
                rating = it.rating,
                coordinatesLatitude = it.coordinatesLatitude,
                coordinatesLongitude = it.coordinatesLongitude,
                transactions = it.transactions,
                price = it.price,
                locationAddress1 = it.locationAddress1,
                locationAddress2 = it.locationAddress2,
                locationAddress3 = it.locationAddress3,
                locationCity = it.locationCity,
                locationZipCode = it.locationZipCode,
                locationCountry = it.locationCountry,
                locationState = it.locationState,
                locationDisplayAddress = it.locationDisplayAddress,
                phone = it.phone,
                displayPhone = it.displayPhone,
                distance = it.distance
            )
        }
}