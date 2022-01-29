package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.BusinessEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BusinessDao {

    @Query("SELECT * FROM businesses")
    fun getBusinesses(): Flow<List<BusinessEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusiness(business: List<BusinessEntity>)

    @Query("DELETE FROM businesses")
    suspend fun deleteBusiness()

    @Transaction
    suspend fun insertAndDeleteBusiness(business: List<BusinessEntity>) {
        deleteBusiness()
        insertBusiness(business)
    }

    @Transaction
    @Query("SELECT * FROM businesses WHERE name LIKE '%'|| :search || '%' ORDER BY rating DESC")
    fun getSearchBusinessesNameRating(search: String): Flow<List<BusinessEntity>>

    @Transaction
    @Query("SELECT * FROM businesses WHERE name LIKE '%'|| :search || '%' ORDER BY distance ASC")
    fun getSearchBusinessesNameDistance(search: String): Flow<List<BusinessEntity>>


//    @Query("SELECT * FROM businesses WHERE location_zip_code LIKE '%'|| :search || '%' ORDER BY CASE WHEN :sortBy = Rating THEN rating END DESC, CASE WHEN :sortBy = Distance THEN distance END DESC")

    @Transaction
    @Query("SELECT * FROM businesses WHERE location_zip_code LIKE '%'|| :search || '%' ORDER BY rating DESC")
    fun getSearchBusinessesZipCodeRating(search: String): Flow<List<BusinessEntity>>

    @Transaction
    @Query("SELECT * FROM businesses WHERE location_zip_code LIKE '%'|| :search || '%' ORDER BY distance ASC")
    fun getSearchBusinessesZipCodeDistance(search: String): Flow<List<BusinessEntity>>


//    @Query("SELECT * FROM businesses WHERE categories_title LIKE '%'|| :search || '%' ORDER BY CASE WHEN :sortBy = Rating THEN rating END DESC, CASE WHEN :sortBy = Distance THEN distance END DESC")

    @Transaction
    @Query("SELECT * FROM businesses WHERE categories_title LIKE '%'|| :search || '%' ORDER BY rating DESC")
    fun getSearchBusinessesCuisineTypeRating(search: String): Flow<List<BusinessEntity>>

    @Transaction
    @Query("SELECT * FROM businesses WHERE categories_title LIKE '%'|| :search || '%' ORDER BY distance ASC")
    fun getSearchBusinessesCuisineTypeDistance(search: String): Flow<List<BusinessEntity>>
}
