package com.example.core.domain.usecase.businesses

import com.example.core.data.Resource
import com.example.core.data.repository.BusinessesRepository
import com.example.core.domain.model.Business
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BusinessesInteractor @Inject constructor(private val businessesRepository: BusinessesRepository) :
    BusinessesUsecase {

    override fun getBusinesses(term: String, latitude: String, longitude: String): Flow<Resource<List<Business>>> =
        businessesRepository.getBusinesses(term, latitude, longitude)

//    override fun updateSubmit(curiculumUpdate: CuriculumUpdate): Flow<Resource<Submit>> =
//        curiculumrepository.updateCuriculum(curiculumUpdate)
//
//    override fun createCuriculum(curiculumCreate: CuriculumCreate): Flow<Resource<Submit>> =
//        curiculumrepository.createCuriculum(curiculumCreate)

    override fun getSearchBusinesses(searchby: String,search: String, sortBy: String): Flow<List<Business>> =
        businessesRepository.getSearchBusiness(searchby,search,sortBy)


}