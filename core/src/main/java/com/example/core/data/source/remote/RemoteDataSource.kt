package com.example.core.data.source.remote

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.business.BusinessResponse
import com.example.core.data.source.remote.response.review.ReviewResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {

    private val tag = RemoteDataSource::class.java.simpleName.toString()

    suspend fun getBusinesses(
        term: String,
        latitude: String,
        longitude: String
    ): Flow<ApiResponse<List<BusinessResponse>>> {
        return flow {
            try {
                val response = apiService.getBusinesses(term,latitude, longitude)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.tag(tag).d("check data response $term + $latitude + $longitude")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getReviewBusinesses(aliases: String): Flow<ApiResponse<List<ReviewResponse>>> {
        return flow {
            try {
                val response = apiService.getBusinessesReview(aliases)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}