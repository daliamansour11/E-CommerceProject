package com.example.e_commerceproject.network.remotesource

import com.example.e_commerceproject.currencyConverter.model.ConverterModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "PonwHXimsWL7N3LyigLfHj3E1Rrj0V9R"
interface ConverterApiService {
//



    @GET("convert?apikey=3GIiFoVpQ5kuwZAZF6XMRKrObHkctBuw&amount=1&from=EGP")
    suspend fun getconvertedCurrencyvalue(

        @Query("to") to: String,
        ): Response<ConverterModel>

   // https://api.apilayer.com/exchangerates_data/convert?apikey=PonwHXimsWL7N3LyigLfHj3E1Rrj0V9R&to=EGP&amount=1&from=USD

    @GET("convert?")//apikey=PonwHXimsWL7N3LyigLfHj3E1Rrj0V9R&amount=1&from=EGP
    suspend fun getconvertedCurrency(
        @Query("apikey") apikey: String = API_KEY,
        @Query("to") to: String,
        @Query("amount") amount: String,
        @Query("from") from: String

    ): Response<ConverterModel>

    companion object {
        var retrofitService: ConverterApiService? = null
        fun getInstance() : ConverterApiService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.apilayer.com/exchangerates_data/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ConverterApiService::class.java)
            }
            return retrofitService!!
        }

    }

}
//    @GET("convert?")
//    suspend fun getconvertedCurrencyvalue(
//        @Query("apikey") apikey: String,
//        @Query("to") to: String,
//        @Query("amount") amount : Long,
//        @Query("from") from: String
//
//    ): Response<ConverterModel>
