package com.patrimesp.cursopremiumandroid.domain.usecase

import com.patrimesp.cursopremiumandroid.domain.entity.Dog
import com.patrimesp.cursopremiumandroid.domain.repository.MainRepository
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(val repository: MainRepository) {
    suspend operator fun invoke(): List<Dog> {
        return repository.getDogs()
    }
}