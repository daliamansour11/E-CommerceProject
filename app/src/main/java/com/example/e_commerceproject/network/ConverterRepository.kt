package com.example.e_commerceproject.network

import android.content.Context
import android.util.Log
import com.example.e_commerceproject.currencyConverter.model.ConverterModel
import com.example.e_commerceproject.network.remotesource.RemoteSourceinterface
import retrofit2.Response

class ConverterRepository(
    val context: Context,
    val remote:RemoteSourceinterface)
    :ConverterRepoInterface{


    companion object {
        private var instance: ConverterRepository? = null
        fun getInstance(
            remoteSource: RemoteSourceinterface,
            context: Context
        ): ConverterRepository {
            return instance ?: ConverterRepository(context,remoteSource
            )
        }
    }
    override suspend fun getConvertedCurrency(): Response<ConverterModel> {
        var response =
            remote.getconvertedCurrency()
        if (response.isSuccessful) {
            System.out.println("We are here")
            Log.e("TAG", "response" + response.body())

            response.body()?.let {

            }

        } else {
            System.out.println("We are here")

            Log.e("TAG", "Error" + response.errorBody())
        }

        return  response
    }

}