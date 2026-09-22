package com.patrimesp.cursopremiumandroid.domain.usecase

import com.patrimesp.cursopremiumandroid.domain.entity.DogDetail
import com.patrimesp.cursopremiumandroid.domain.repository.MainRepository
import javax.inject.Inject

class GetDogByIdUseCase @Inject constructor(val repository: MainRepository) {
    suspend operator fun invoke(id: Int): DogDetail {
        return repository.getDogById(id)
    }
}