package com.patrimesp.cursopremiumandroid.data.repository

import com.patrimesp.cursopremiumandroid.data.datasource.api.ApiService
import com.patrimesp.cursopremiumandroid.data.mapper.toDomain
import com.patrimesp.cursopremiumandroid.domain.entity.Dog
import com.patrimesp.cursopremiumandroid.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(val api: ApiService): MainRepository {
    override suspend fun getDogs(): List<Dog> {
        return api.getDogs().map { it.toDomain() }
    }
}