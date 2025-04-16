package com.example.passkeydemo

import android.annotation.SuppressLint
import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.GetPasswordOption
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
class PasskeyViewModel (
    private val credentialManager: CredentialManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(PasskeyUiState())
    val uiState: StateFlow<PasskeyUiState> = _uiState


    fun signInWithPasskey() {


    }

    fun signInWithCredentialManager(
        activityContext: Context,
        onSuccess: (GetCredentialResponse) -> Unit,
        onError: (GetCredentialException) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(
                    context = activityContext,
                    request = GetCredentialRequest(
                        // 先简单只用密码登录请求
                        credentialOptions = listOf(GetPasswordOption())
                    )
                )
                onSuccess(result)
            } catch (e: GetCredentialException) {
                onError(e)
            }
        }
    }

    fun updateMessage(msg: String) {
        _uiState.value = _uiState.value.copy(message = msg)
    }

}