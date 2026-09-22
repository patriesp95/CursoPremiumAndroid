package com.patrimesp.cursopremiumandroid.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrimesp.cursopremiumandroid.domain.usecase.GetDogByIdUseCase
import com.patrimesp.cursopremiumandroid.domain.entity.DogDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getDogByIdUseCase: GetDogByIdUseCase): ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadDogById(id: Int) {
        viewModelScope.launch(Dispatchers.IO){
            _uiState.update { it.copy(isLoading = true, error = null, dog = null) }
            try {
                val dog = getDogByIdUseCase(id)
                _uiState.update { it.copy(isLoading = false, dog = dog) }
            } catch (error: Exception) {
                if (error is CancellationException) throw error
                _uiState.update { it.copy(isLoading = false, error = error.message ?: "No se pudo cargar el perro") }
            }
        }
    }
}

data class DetailUiState(
    val isLoading: Boolean = false,
    val dog: DogDetail? = null,
    val error: String? = null
)
