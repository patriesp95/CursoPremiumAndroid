package com.patrimesp.cursopremiumandroid.data.response

import kotlinx.serialization.Serializable

@Serializable
data class MainResponse (
    val id: Int,
    val name: String,
    val breed: String,
    val age: Int,
    val description: String,
    val image: String,
)