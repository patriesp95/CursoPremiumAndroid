package com.patrimesp.cursopremiumandroid.view.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit
) {
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
}