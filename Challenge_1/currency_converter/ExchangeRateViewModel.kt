package com.example.currency_converter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExchangeRateViewModel : ViewModel() {
    private val repository = ExchangeRateRepository()

    private val _exchangeRates = MutableStateFlow<Map<String, Double>>(emptyMap())
    val exchangeRates: StateFlow<Map<String, Double>> get() = _exchangeRates

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun fetchExchangeRates() {
        viewModelScope.launch {
            try {
                val response = repository.fetchExchangeRates()
                if (response.isSuccessful) {
                    response.body()?.let { exchangeRateResponse ->
                        // Logging the base currency and date fetched
                        Log.d("ExchangeRateViewModel", "Base currency: ${exchangeRateResponse.base}, Date: ${exchangeRateResponse.date}")
                        // Updating the exchange rates from the 'rates' field in the response
                        _exchangeRates.value = exchangeRateResponse.rates
                    } ?: run {
                        Log.e("ExchangeRateViewModel", "Response body is null")
                    }
                } else {
                    _error.value = "Error fetching exchange rates: ${response.message()}"
                    Log.e("ExchangeRateViewModel", "Error fetching exchange rates: ${response.message()}")
                }
            } catch (e: Exception) {
                _error.value = "An error occurred: ${e.message}"
                Log.e("ExchangeRateViewModel", "An error occurred: ${e.message}", e)
            }
        }
    }

    /**
     * Calculates the converted amount based on exchange rates.
     * @param amount The amount in the source currency.
     * @param sourceCurrency The source currency code.
     * @param targetCurrency The target currency code.
     * @return The converted amount, or null if exchange rates are not available.
     */
    fun calculateConvertedAmount(
        amount: Double,
        sourceCurrency: String,
        targetCurrency: String
    ): Double? {
        val rates = _exchangeRates.value
        val sourceRate = rates[sourceCurrency]
        val targetRate = rates[targetCurrency]

        return if (sourceRate != null && targetRate != null) {
            amount * (targetRate / sourceRate)
        } else {
            null // Rates are not available
        }
    }
}
