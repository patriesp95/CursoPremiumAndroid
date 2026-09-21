package com.patrimesp.cursopremiumandroid.view.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(): ViewModel() {

    val _uiState = MutableStateFlow(DetailUiState())
    var uiState: StateFlow<DetailUiState> = _uiState

}

data class DetailUiState(
    val isLoading: Boolean = false
)