package com.example.e_commerceproject.network.remotesource

import android.util.Log
import com.example.e_commerceproject.authentication.register.model.CustomerAddress
import com.example.e_commerceproject.authentication.register.model.CustomerModel
import retrofit2.Response

class AdressRepository(private val retrofitService: RetrofitService) {

    suspend fun postAddress( customer_id : String ,address : CustomerModel) : Response<CustomerModel> {

        val n = retrofitService.addAddress(customer_id , address)
        Log.i("TAG", "posttttcustomerAdrressssssssssssssssssss:${n.body()},${n.code()}")
       return n
    }

    suspend fun getAddress(id :String) : Response<CustomerAddress> {

        val n = retrofitService.getAddress(id )
        Log.i("TAG", "posttttcustomerAdrressssssssssssssssssssssss: ${n.body()}  ${n.code()}")
        return n
    }


}