package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Business
import kotlinx.coroutines.flow.Flow

interface IBusinessesRepository {

    fun getBusinesses(term: String, latitude: String, longitude: String) : Flow<Resource<List<Business>>>

//    fun updateBusiness(businessUpdate: BusinessUpdate) : Flow<Resource<Submit>>
//
//    fun createCuriculum(curiculumCreate: BusinessCreate) : Flow<Resource<Submit>>

    fun getSearchBusiness(searchby: String, search: String, sortBy: String): Flow<List<Business>>
}