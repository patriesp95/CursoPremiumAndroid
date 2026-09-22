package com.patrimesp.cursopremiumandroid.domain.repository

import com.patrimesp.cursopremiumandroid.domain.entity.Dog
import com.patrimesp.cursopremiumandroid.domain.entity.DogDetail

interface MainRepository {
    suspend fun getDogs(): List<Dog>
    suspend fun getDogById(id: Int): DogDetail
}