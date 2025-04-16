package com.example.passkeydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passkeydemo.ui.PasskeyScreen

class MainActivity : ComponentActivity() {
    private val viewModel: PasskeyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasskeyScreen(viewModel)
        }
    }
}