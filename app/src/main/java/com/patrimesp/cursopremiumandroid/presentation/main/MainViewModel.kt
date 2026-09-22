package com.patrimesp.cursopremiumandroid.presentation.main

import androidx.lifecycle.ViewModel
import com.patrimesp.cursopremiumandroid.domain.usecase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val dogsUseCase: GetDogsUseCase): ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

}

data class MainUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)