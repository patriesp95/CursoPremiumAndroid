package com.patrimesp.cursopremiumandroid.view.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    val _uiState = MutableStateFlow(MainUiState())
    var uiState: StateFlow<MainUiState> = _uiState

}

data class MainUiState(
    val isLoading: Boolean = false
)