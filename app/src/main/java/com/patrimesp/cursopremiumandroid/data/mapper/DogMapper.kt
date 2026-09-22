package com.patrimesp.cursopremiumandroid.data.mapper

import com.patrimesp.cursopremiumandroid.data.datasource.api.ApiConfig.BASE_URL
import com.patrimesp.cursopremiumandroid.data.response.MainResponse
import com.patrimesp.cursopremiumandroid.domain.entity.Dog
fun MainResponse.toDomain(): Dog {
    return Dog(
        id = id,
        name = name,
        description = description,
        age = age,
        image = BASE_URL + image
    )
}