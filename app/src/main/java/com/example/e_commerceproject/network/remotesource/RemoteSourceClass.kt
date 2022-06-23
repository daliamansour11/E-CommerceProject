package com.example.e_commerceproject.network.remotesource

import com.example.e_commerceproject.currencyConverter.model.ConverterModel
import retrofit2.Response

class RemoteSourceClass :RemoteSourceinterface {
    override suspend fun getconvertedCurrency(to:String): Response<ConverterModel> {

        val apiService =  RetrofitConverter.getInstance().create(ConverterApiService::class.java)
        var response =  apiService.getconvertedCurrencyvalue(to)

        return  response
    }
    companion object{
        private var instance: RemoteSourceClass? = null
        fun getInstance():RemoteSourceClass {
            return  instance?:RemoteSourceClass()
        }
    }
}