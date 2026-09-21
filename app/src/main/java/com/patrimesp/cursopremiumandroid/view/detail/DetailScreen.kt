package com.patrimesp.cursopremiumandroid.view.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patrimesp.cursopremiumandroid.view.main.MainViewModel

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val uiState by detailViewModel.uiState.collectAsStateWithLifecycle()
}