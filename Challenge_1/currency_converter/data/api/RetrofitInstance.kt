package com.example.currency_converter.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://api.exchangeratesapi.io/v1/"

    val apiService: ExchangeRateAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeRateAPIService::class.java)
    }
}