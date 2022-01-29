package com.example.core.domain.usecase.reviewbusiness

import com.example.core.data.Resource
import com.example.core.domain.model.BusinessReview
import kotlinx.coroutines.flow.Flow

interface BusinessReviewUsecase {

    fun getBusinessReview(aliases: String): Flow<Resource<List<BusinessReview>>>
}