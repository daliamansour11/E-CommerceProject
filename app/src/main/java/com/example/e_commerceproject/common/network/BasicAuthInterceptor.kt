package com.example.e_commerceproject.common.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor(username: String, password: String): Interceptor  {

    private val credentials: String

    init{
        credentials = Credentials.basic(username, password)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request =  chain.request()
        var authenticatedRequest = request.newBuilder()
            .header("Authorization", credentials).build()

        var response = chain.proceed(authenticatedRequest)
        return response
    }
}