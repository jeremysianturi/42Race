package com.example.core.data.source.local.room

import com.example.core.data.source.local.entity.BusinessEntity
import com.example.core.data.source.local.entity.BusinessReviewEntity
import com.example.core.data.source.local.room.dao.BusinessDao
import com.example.core.data.source.local.room.dao.BusinessReviewDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val mBusinessDao: BusinessDao,
    private val mBusinessReviewDao: BusinessReviewDao
) {

    // Business
    fun getBusinesses(): Flow<List<BusinessEntity>> = mBusinessDao.getBusinesses()

    suspend fun insertBusiness(business: List<BusinessEntity>) =
        mBusinessDao.insertAndDeleteBusiness(business)

    suspend fun deleteBusiness() = mBusinessDao.deleteBusiness()

    // BUSINESS NAME
    fun getSearchBusinessNameRating(search: String): Flow<List<BusinessEntity>> =
        mBusinessDao.getSearchBusinessesNameRating(search)
    fun getSearchBusinessNameDistance(search: String): Flow<List<BusinessEntity>> =
        mBusinessDao.getSearchBusinessesNameDistance(search)

    // POSTAL CODE
    fun getSearchBusinessZipCodeRating(search: String): Flow<List<BusinessEntity>> =
        mBusinessDao.getSearchBusinessesZipCodeRating(search)
    fun getSearchBusinessZipCodeDistance(search: String): Flow<List<BusinessEntity>> =
        mBusinessDao.getSearchBusinessesZipCodeDistance(search)

    // CUISINE TYPE
    fun getSearchBusinessCuisineTypeRating(search: String): Flow<List<BusinessEntity>> =
        mBusinessDao.getSearchBusinessesCuisineTypeRating(search)
    fun getSearchBusinessCuisineTypeDistance(search: String): Flow<List<BusinessEntity>> =
        mBusinessDao.getSearchBusinessesCuisineTypeDistance(search)



    // BusinessReview
    fun getBusinessesReview(): Flow<List<BusinessReviewEntity>> = mBusinessReviewDao.getBusinessReview()

    suspend fun insertBusinessReview(businessReview: List<BusinessReviewEntity>) =
        mBusinessReviewDao.insertAndDeleteBusinessReview(businessReview)

    suspend fun deleteBusinessReview() = mBusinessReviewDao.deleteBusinessReview()

}