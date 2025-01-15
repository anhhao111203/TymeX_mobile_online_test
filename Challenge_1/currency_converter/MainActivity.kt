package com.example.currency_converter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currency_converter.ui.theme.Currency_converterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ExchangeRateViewModel = viewModel()
            Currency_converterTheme {
                // Passing 'this' as context to the CurrencyConverterApp composable
                CurrencyConverterApp(viewModel = viewModel, context = this)
            }
        }
    }
}
