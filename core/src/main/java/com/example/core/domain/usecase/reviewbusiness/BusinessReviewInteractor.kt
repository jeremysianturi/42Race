package com.example.core.domain.usecase.reviewbusiness

import com.example.core.data.Resource
import com.example.core.data.repository.BusinessReviewRepository
import com.example.core.domain.model.BusinessReview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BusinessReviewInteractor @Inject constructor(private val businessReviewRepository: BusinessReviewRepository) :
    BusinessReviewUsecase {

    override fun getBusinessReview(aliases: String): Flow<Resource<List<BusinessReview>>> =
        businessReviewRepository.getBusinessReview(aliases)

//    override fun updateSubmit(curiculumUpdate: CuriculumUpdate): Flow<Resource<Submit>> =
//        curiculumrepository.updateCuriculum(curiculumUpdate)
//
//    override fun createCuriculum(curiculumCreate: CuriculumCreate): Flow<Resource<Submit>> =
//        curiculumrepository.createCuriculum(curiculumCreate)


}