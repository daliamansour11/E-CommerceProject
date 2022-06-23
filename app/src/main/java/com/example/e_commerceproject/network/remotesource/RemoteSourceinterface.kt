package com.example.e_commerceproject.network.remotesource

import com.example.e_commerceproject.currencyConverter.model.ConverterModel
import retrofit2.Response

interface RemoteSourceinterface {

    suspend fun getconvertedCurrency(to:String) : Response<ConverterModel>

}