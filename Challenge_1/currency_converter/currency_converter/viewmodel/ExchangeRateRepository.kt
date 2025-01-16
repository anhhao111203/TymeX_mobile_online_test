package com.example.currency_converter.viewmodel

import com.example.currency_converter.data.api.RetrofitInstance
import com.example.currency_converter.data.model.ExchangeRateResponse
import com.example.currency_converter.utils.Constants
import retrofit2.Response

class ExchangeRateRepository {
    suspend fun fetchExchangeRates(): Response<ExchangeRateResponse> {
        return RetrofitInstance.apiService.getExchangeRates(Constants.API_KEY)
    }
}