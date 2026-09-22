package com.patrimesp.cursopremiumandroid.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrimesp.cursopremiumandroid.domain.entity.Dog
import com.patrimesp.cursopremiumandroid.domain.usecase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getDogsUseCase: GetDogsUseCase): ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private var allDogs = emptyList<Dog>()

    init {
        loadDogs()
    }

    fun loadDogs() {
        viewModelScope.launch(Dispatchers.IO){
            _uiState.update { state -> state.copy(isLoading = true) }
            try {
                allDogs = getDogsUseCase()
                _uiState.update { state -> state.copy(isLoading = false, dogs = allDogs) }
            } catch(error: Exception){
                _uiState.update { state -> state.copy(isLoading = false, error = error.message) }
            }
        }
    }

    fun onDogSearched(query: String){
        val filteredDogs = allDogs.filter { dog ->
            dog.name.contains(query, ignoreCase = true) ||
                    dog.breed.contains(query, ignoreCase = true)
        }
        _uiState.update { state -> state.copy(query = query, dogs = filteredDogs) }
    }


}

data class MainUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val dogs: List<Dog> = emptyList(),
    var query: String = ""
)