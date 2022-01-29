package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.business.ListBusinessResponse
import com.example.core.data.source.remote.response.review.ListReviewResponse
import okhttp3.internal.userAgent
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // businesses
    @GET("search")
    suspend fun getBusinesses(
        @Query("term") term: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
    ): ListBusinessResponse

    // businesses review
    @GET("{aliases}/reviews")
    suspend fun getBusinessesReview(@Path(value = "aliases",encoded = true) aliases : String
    ): ListReviewResponse

//    @GET("users/{user_id}/playlists")
//    Call<List<Playlist> getUserPlaylists(@Path(value = "user_id", encoded = true) String userId);
}