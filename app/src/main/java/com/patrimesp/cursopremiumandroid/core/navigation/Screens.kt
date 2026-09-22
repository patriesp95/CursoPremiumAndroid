package com.patrimesp.cursopremiumandroid.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Main: NavKey

@Serializable
data class Detail(val id: Int): NavKey