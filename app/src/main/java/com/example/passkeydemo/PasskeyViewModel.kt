package com.example.passkeydemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PasskeyViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(PasskeyUiState())
    val uiState: StateFlow<PasskeyUiState> = _uiState

    fun onLoginClicked() {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            message = "Login clicked"
        )

    }
}