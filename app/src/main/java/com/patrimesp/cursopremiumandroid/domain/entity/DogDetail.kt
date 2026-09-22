package com.patrimesp.cursopremiumandroid.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class DogDetail (
    val id: Int,
    val name: String,
    val breed: String,
    val age: Int,
    val description: String,
    val image: String,
    val weight: String,
    val origin: String,
    val temperament: String
)