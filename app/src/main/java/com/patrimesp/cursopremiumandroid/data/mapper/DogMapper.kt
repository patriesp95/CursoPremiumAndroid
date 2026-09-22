package com.patrimesp.cursopremiumandroid.data.mapper

import com.patrimesp.cursopremiumandroid.data.datasource.api.ApiConfig.BASE_URL
import com.patrimesp.cursopremiumandroid.data.response.DetailResponse
import com.patrimesp.cursopremiumandroid.data.response.MainResponse
import com.patrimesp.cursopremiumandroid.domain.entity.Dog
import com.patrimesp.cursopremiumandroid.domain.entity.DogDetail

fun MainResponse.toDomain(): Dog {
    return Dog(
        id = id,
        name = name,
        breed = breed,
        description = description,
        age = age,
        image = BASE_URL + image
    )
}

fun DetailResponse.toDomain(): DogDetail {
    return DogDetail(
        id = id,
        name = name,
        breed = breed,
        description = description,
        age = age,
        image = BASE_URL + image,
        weight = weight,
        origin = origin,
        temperament = temperament
    )
}