package com.example.e_commerceproject.data.remotesource

import com.example.e_commerceproject.currencyConverter.model.ConverterModel
import retrofit2.Response

interface RemoteSourceinterface {
    suspend fun getconvertedCurrency() : Response<ConverterModel>

}