package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.review.ReviewResponse
import com.example.core.domain.model.BusinessReview
import com.example.core.domain.repository.IBusinessReviewRepository
import com.example.core.helper.datamapper.DataMapperBusinessReview
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusinessReviewRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IBusinessReviewRepository {

    override fun getBusinessReview(aliases: String): Flow<Resource<List<BusinessReview>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<BusinessReview>, List<ReviewResponse>>() {

            override fun loadFromDB(): Flow<List<BusinessReview>> {
                return localDataSource.getBusinessesReview().map {
                    DataMapperBusinessReview.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<BusinessReview>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<ReviewResponse>>> =
                remoteDataSource.getReviewBusinesses(aliases)

            override suspend fun saveCallResult(data: List<ReviewResponse>) {
                val list = DataMapperBusinessReview.mapResponsetoEntities(data)
                localDataSource.insertBusinessReview(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteBusinessReview()
            }

        }.asFlow()

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


}