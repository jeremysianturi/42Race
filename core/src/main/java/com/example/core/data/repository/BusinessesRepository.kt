package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.business.BusinessResponse
import com.example.core.domain.model.Business
import com.example.core.domain.repository.IBusinessesRepository
import com.example.core.helper.datamapper.DataMapperBusinesses
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusinessesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IBusinessesRepository {

    override fun getBusinesses(term: String, latitude: String, longitude: String): Flow<Resource<List<Business>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<Business>, List<BusinessResponse>>() {

            override fun loadFromDB(): Flow<List<Business>> {
                return localDataSource.getBusinesses().map {
                    DataMapperBusinesses.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<Business>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<BusinessResponse>>> =
                remoteDataSource.getBusinesses(term, latitude, longitude)

            override suspend fun saveCallResult(data: List<BusinessResponse>) {
                val list = DataMapperBusinesses.mapResponsetoEntities(data)
                localDataSource.insertBusiness(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteBusiness()
            }

        }.asFlow()



    override fun getSearchBusiness(searchby: String, search: String, sortBy: String): Flow<List<Business>> {

        if (searchby.equals("Business Name")){
            if (sortBy.equals("Rating")){
                return localDataSource.getSearchBusinessNameRating(search).map { DataMapperBusinesses.mapEntitiestoDomain(it) }
            } else {
                return localDataSource.getSearchBusinessNameRating(search).map { DataMapperBusinesses.mapEntitiestoDomain(it) }
            }

        } else if (searchby.equals("Postal Code")) {

            if (sortBy.equals("Rating")){
                return localDataSource.getSearchBusinessZipCodeRating(search).map { DataMapperBusinesses.mapEntitiestoDomain(it) }
            } else {
                return localDataSource.getSearchBusinessZipCodeDistance(search).map { DataMapperBusinesses.mapEntitiestoDomain(it) }
            }

        } else if (searchby.equals("Cuisine Type")) {

            if (sortBy.equals("Rating")){
                return localDataSource.getSearchBusinessCuisineTypeRating(search).map { DataMapperBusinesses.mapEntitiestoDomain(it) }
            } else {
                return localDataSource.getSearchBusinessCuisineTypeDistance(search).map { DataMapperBusinesses.mapEntitiestoDomain(it) }
            }

        }

        else { return localDataSource.getSearchBusinessNameRating(search).map { DataMapperBusinesses.mapEntitiestoDomain(it) } }

    }


}

//    override fun updateBusiness(businessUpdate: BusinessUpdate): Flow<Resource<Submit>> =
//        object : NetworkBoundResource<Submit, SubmitResponse>() {
//
//            override fun loadFromDB(): Flow<Submit> {
//                return localDataSource.getSubmitResponse().map {
//                    DataMapperSubmit.mapEntitiestoDomain(it)
//                }
//            }
//
//            override fun shouldFetch(data: Submit?): Boolean =
//                true
//
//            override suspend fun createCall(): Flow<ApiResponse<SubmitResponse>> =
//                remoteDataSource.updateCuriculum(curiculumUpdate)
//
//            override suspend fun saveCallResult(data: SubmitResponse) {
//                val list = DataMapperSubmit.mapResponsetoEntities(data)
//                localDataSource.insertSubmitResponse(list)
//            }
//
//        }.asFlow()

//    override fun createCuriculum(curiculumCreate: CuriculumCreate): Flow<Resource<Submit>> =
//        object : NetworkBoundResource<Submit, SubmitResponse>() {
//
//            override fun loadFromDB(): Flow<Submit> {
//                return localDataSource.getSubmitResponse().map {
//                    DataMapperSubmit.mapEntitiestoDomain(it)
//                }
//            }
//
//            override fun shouldFetch(data: Submit?): Boolean =
//                true
//
//            override suspend fun createCall(): Flow<ApiResponse<SubmitResponse>> =
//                remoteDataSource.createCuriculum(curiculumCreate)
//
//            override suspend fun saveCallResult(data: SubmitResponse) {
//                val list = DataMapperSubmit.mapResponsetoEntities(data)
//                localDataSource.insertSubmitResponse(list)
//            }
//
//        }.asFlow()