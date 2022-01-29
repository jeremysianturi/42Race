package com.example.core.helper.datamapper

import com.example.core.data.source.local.entity.BusinessReviewEntity
import com.example.core.data.source.remote.response.review.ReviewResponse
import com.example.core.domain.model.BusinessReview

object DataMapperBusinessReview {

    fun mapResponsetoEntities(input: List<ReviewResponse>): List<BusinessReviewEntity> {
        val businessReviewList = ArrayList<BusinessReviewEntity>()
        input.map {
            val businessReview = BusinessReviewEntity(
                id = it.id,
                url = it.url,
                text = it.text,
                rating = it.rating,
                timeCreated = it.timeCreated,
                userId = it.user.id,
                userProfileUrl = it.user.profileUrl,
                userImageUrl = it.user.imageUrl ?: "",
                userName = it.user.name

            )
            businessReviewList.add(businessReview)
        }

        return businessReviewList
    }

    fun mapEntitiestoDomain(input: List<BusinessReviewEntity>): List<BusinessReview> =
        input.map {
            BusinessReview(
                id = it.id,
                url = it.url,
                text = it.text,
                rating = it.rating,
                timeCreated = it.timeCreated,
                userId = it.userId,
                userProfileUrl = it.userProfileUrl,
                userImageUrl = it.userImageUrl,
                userName = it.userName
            )
        }
}