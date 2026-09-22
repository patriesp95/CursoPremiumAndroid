package com.patrimesp.cursopremiumandroid.data.di

import com.patrimesp.cursopremiumandroid.data.datasource.api.ApiConfig.BASE_URL
import com.patrimesp.cursopremiumandroid.data.datasource.api.ApiService
import com.patrimesp.cursopremiumandroid.data.repository.MainRepositoryImpl
import com.patrimesp.cursopremiumandroid.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideMainRepository(api: ApiService): MainRepository = MainRepositoryImpl(api = api)

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

}