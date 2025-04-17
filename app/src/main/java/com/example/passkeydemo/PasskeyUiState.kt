package com.example.passkeydemo

data class PasskeyUiState(
    val isSignedIn: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = false
)