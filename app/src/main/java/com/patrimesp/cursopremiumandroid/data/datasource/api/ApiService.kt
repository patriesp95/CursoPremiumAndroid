package com.patrimesp.cursopremiumandroid.data.datasource.api

import com.patrimesp.cursopremiumandroid.data.response.DetailResponse
import com.patrimesp.cursopremiumandroid.data.response.MainResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("dogs.json")
    suspend fun getItems():List<MainResponse>

    @GET("details/{id}.json")
    suspend fun getItembyId(@Path("id") id: Int): DetailResponse
}
