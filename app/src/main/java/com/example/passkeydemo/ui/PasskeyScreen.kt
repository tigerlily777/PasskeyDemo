package com.example.passkeydemo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passkeydemo.PasskeyViewModel

@Composable
fun PasskeyScreen(viewModel: PasskeyViewModel) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { viewModel.signInWithPasskey() }) {
                Text("Sign in with Passkey")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = uiState.value.message ?: "No message")
        }
    }
}

@Preview
@Composable
fun PreviewPasskeyScreen(
) {
    PasskeyScreen(viewModel = hiltViewModel())
}