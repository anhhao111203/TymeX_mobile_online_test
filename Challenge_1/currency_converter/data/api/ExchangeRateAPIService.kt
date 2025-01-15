package com.example.currency_converter.data.api

import com.example.currency_converter.data.model.ExchangeRateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateAPIService {
    @GET("latest")
    suspend fun getExchangeRates(
        @Query("access_key") apiKey: String
    ): Response<ExchangeRateResponse>
}