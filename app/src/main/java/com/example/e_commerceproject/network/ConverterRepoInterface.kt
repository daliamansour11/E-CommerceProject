package com.example.e_commerceproject.network

import com.example.e_commerceproject.currencyConverter.model.ConverterModel
import retrofit2.Response

interface ConverterRepoInterface {
    suspend fun  getConvertedCurrency(): Response<ConverterModel>

}