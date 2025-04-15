package com.example.passkeydemo

import androidx.lifecycle.ViewModel
import com.example.passkeydemo.ui.CredentialUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PasskeyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CredentialUiState())
    val uiState: StateFlow<CredentialUiState> = _uiState

    fun signInWithPasskey() {
        // TODO: use CredentialManager handle passkey login
    }

    fun updateMessage(msg: String) {
        _uiState.value = _uiState.value.copy(message = msg)
    }

}