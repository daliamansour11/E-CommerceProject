package com.example.e_commerceproject.data.remotesource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConverter {

    const val baseUrl = "https://api.apilayer.com/exchangerates_data/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}