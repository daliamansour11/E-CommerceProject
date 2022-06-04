package com.example.e_commerceproject.common.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitAPIClient {

    companion object {
        private val BASE_URL = "https://madalex20220.myshopify.com/admin/api/2022-01/"
        private val USERNAME = "9d169ad72dd7620e70f56b28ae6146d9"
        private val PASSWORD = "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"
        private var apiClient: APIClient? = null

        fun getAPIClientInstance(): APIClient {
            if (apiClient == null) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)

                var okHttpClient: OkHttpClient = OkHttpClient.Builder()
//                    .addInterceptor(logging)
                    .addInterceptor(BasicAuthInterceptor(USERNAME, PASSWORD))
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build()

                var retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

                apiClient = retrofit.create(APIClient::class.java)
            }
            return apiClient!!

        }

    }
}