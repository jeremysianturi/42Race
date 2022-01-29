package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.BusinessEntity
import com.example.core.data.source.local.entity.BusinessReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BusinessReviewDao {

    @Query("SELECT * FROM business_review")
    fun getBusinessReview(): Flow<List<BusinessReviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusinessReview(businessReview: List<BusinessReviewEntity>)

    @Query("DELETE FROM business_review")
    suspend fun deleteBusinessReview()

    @Transaction
    suspend fun insertAndDeleteBusinessReview(businessReview: List<BusinessReviewEntity>) {
        deleteBusinessReview()
        insertBusinessReview(businessReview)
    }
}