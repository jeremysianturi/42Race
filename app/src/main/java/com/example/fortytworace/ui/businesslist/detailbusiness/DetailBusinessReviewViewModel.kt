package com.example.fortytworace.ui.businesslist.detailbusiness

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.reviewbusiness.BusinessReviewUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DetailBusinessReviewViewModel @ViewModelInject constructor(
    private val businessReviewUsecase: BusinessReviewUsecase,
) : ViewModel(){

    fun getbusinessReview(aliases: String) =
        businessReviewUsecase.getBusinessReview(aliases).asLiveData()

}