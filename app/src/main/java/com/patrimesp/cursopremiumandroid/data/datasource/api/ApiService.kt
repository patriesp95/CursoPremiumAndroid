package com.patrimesp.cursopremiumandroid.data.datasource.api

import com.patrimesp.cursopremiumandroid.data.response.MainResponse
import retrofit2.http.GET

interface ApiService {
    @GET("dogs.json")
    suspend fun getDogs():List<MainResponse>
}