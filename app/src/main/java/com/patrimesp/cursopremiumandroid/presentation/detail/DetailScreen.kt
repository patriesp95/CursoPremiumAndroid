package com.patrimesp.cursopremiumandroid.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val uiState by detailViewModel.uiState.collectAsStateWithLifecycle()
}