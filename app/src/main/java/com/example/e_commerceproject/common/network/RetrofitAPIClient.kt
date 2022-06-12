package com.example.e_commerceproject.common.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitAPIClient {

    companion object {
        private val BASE_URL = "https://madalex20220.myshopify.com/admin/api/2022-01/"
        private var apiClient: APIClient? = null

        fun getAPIClientInstance(): APIClient {
            if (apiClient == null) {
                var retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiClient = retrofit.create(APIClient::class.java)
            }
            return apiClient!!

        }

    }
}