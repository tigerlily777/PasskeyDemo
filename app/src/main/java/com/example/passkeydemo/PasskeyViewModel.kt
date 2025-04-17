package com.example.passkeydemo

import android.annotation.SuppressLint
import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.GetPublicKeyCredentialOption
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class PasskeyViewModel @Inject constructor(
    private val credentialManager: CredentialManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(PasskeyUiState())
    val uiState: StateFlow<PasskeyUiState> = _uiState


    fun signInWithPasskey(context: Context) {
        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(
                    context = context,
                    request = GetCredentialRequest(
                        credentialOptions = listOf(
                            GetPublicKeyCredentialOption(requestJson = createFakePasskeyRequestJson()),
                            GetPasswordOption()
                        )
                    )
                )
                _uiState.value = _uiState.value.copy(
                    isSignedIn = true,
                    message = "Sign-in successful! Credential type: ${result.credential.javaClass.simpleName}"
                )

            } catch (e: GetCredentialException) {
                _uiState.value = _uiState.value.copy(
                    message = "Sign-in failed: ${e.message}"
                )
            }
        }


    }

    private fun createFakePasskeyRequestJson(): String {
        return """
        {
          "challenge": "fakeChallenge123",
          "rp": {
            "name": "Passkey Demo App",
            "id": "passkey-demo.example.com"
          },
          "user": {
            "id": "fakeUserId456",
            "name": "user@example.com",
            "displayName": "user@example.com"
          },
          "pubKeyCredParams": [
            {"type": "public-key", "alg": -7},
            {"type": "public-key", "alg": -257}
          ],
          "timeout": 60000,
          "attestation": "none",
          "authenticatorSelection": {
            "authenticatorAttachment": "platform",
            "requireResidentKey": true,
            "residentKey": "required",
            "userVerification": "required"
          }
        }
    """.trimIndent()
    }

}