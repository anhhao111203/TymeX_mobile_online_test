package com.example.currency_converter.data.model

data class ExchangeRateResponse(
    val success: Boolean = false,
    val timestamp: Long = 0L,
    val base: String = "",
    val date: String = "",
    val rates: Map<String, Double> = emptyMap()
)
