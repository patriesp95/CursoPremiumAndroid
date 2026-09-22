package com.patrimesp.cursopremiumandroid.domain.repository

import com.patrimesp.cursopremiumandroid.domain.entity.Dog

interface MainRepository {
    suspend fun getDogs(): List<Dog>
}