package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.BusinessReview
import kotlinx.coroutines.flow.Flow

interface IBusinessReviewRepository {

    fun getBusinessReview(aliases: String) : Flow<Resource<List<BusinessReview>>>

}