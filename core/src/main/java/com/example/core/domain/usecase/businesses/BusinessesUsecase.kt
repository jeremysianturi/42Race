package com.example.core.domain.usecase.businesses

import com.example.core.data.Resource
import com.example.core.domain.model.Business
import kotlinx.coroutines.flow.Flow

interface BusinessesUsecase {

    fun getBusinesses(term: String, latitude: String, longitude: String): Flow<Resource<List<Business>>>

//    fun updateSubmit(curiculumUpdate: CuriculumUpdate) : Flow<Resource<Submit>>
//
//    fun createCuriculum(curiculumCreate: CuriculumCreate) : Flow<Resource<Submit>>

    fun getSearchBusinesses(searchby: String, search: String, sortBy: String): Flow<List<Business>>
}