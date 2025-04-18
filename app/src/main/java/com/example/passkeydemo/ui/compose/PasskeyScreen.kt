package com.example.passkeydemo.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.passkeydemo.PasskeyViewModel

@Composable
fun PasskeyScreen(viewModel: PasskeyViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) } // ✨
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            val context = LocalContext.current
            Button(
                onClick = { viewModel.signInWithPasskey(context = context) },
                enabled = !uiState.value.isLoading
            ) {
                if (uiState.value.isLoading) {
                    Text("Loading...")
                } else {
                    Text("Sign in with Passkey")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // ✨ 如果 uiState 的 message 有变化，就弹出 snackbar
    LaunchedEffect(uiState.value.message) {
        uiState.value.message?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearMessage()
        }
    }
}

@Preview
@Composable
fun PreviewPasskeyScreen(
) {
    PasskeyScreen(viewModel = hiltViewModel())
}