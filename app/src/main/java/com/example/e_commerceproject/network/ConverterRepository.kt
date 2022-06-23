package com.example.e_commerceproject.network

import android.content.Context
import android.util.Log
import com.example.e_commerceproject.currencyConverter.model.ConverterModel
import com.example.e_commerceproject.network.remotesource.ConverterApiService
import com.example.e_commerceproject.network.remotesource.RemoteSourceinterface
import com.example.e_commerceproject.network.remotesource.RetrofitService
import retrofit2.Response

class ConverterRepository(private  val  retrofitService: ConverterApiService)
{
//    val context: Context,
//    val remote:RemoteSourceinterface)
//    :ConverterRepoInterface {
//
//
//    companion object {
//        private var instance: ConverterRepository? = null
//        fun getInstance(
//            remoteSource: RemoteSourceinterface,
//            context: Context
//        ): ConverterRepository {
//            return instance ?: ConverterRepository(
//                context, remoteSource
//            )
//        }
//    }

//     suspend fun getConvertedCurrency(to: String): Response<ConverterModel> {
//        var response =
//            remote.getconvertedCurrency(to)
//        if (response.isSuccessful) {
//            System.out.println("We are here")
//            Log.e("TAG", "response" + response.body())
//
//            response.body()?.let {
//
//            }
//
//        } else {
//            System.out.println("We are here")
//
//            Log.e("TAG", "Error" + response.errorBody())
//        }
//
//        return response
//    }
suspend fun  getCurrency( api : String,to :String, amount:String,from :String):Response<ConverterModel> {
    val currency = retrofitService.getconvertedCurrency(api,to,amount,from)
    println("------------------getttt---------------------")
    Log.i("TAG", "getpostitem: ${currency.body()}  ${currency.code()}")
    println("--------------------getttt-----")


    return currency

}
}