package com.example.e_commerceproject.network.remotesource

import com.example.e_commerceproject.currencyConverter.model.ConverterModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val api = ""
interface ConverterApiService {

    @GET("convert?apikey=PonwHXimsWL7N3LyigLfHj3E1Rrj0V9R&amount=1&from=EGP")
    suspend fun getconvertedCurrencyvalue(

        @Query("to") to: String,


    ): Response<ConverterModel>
}