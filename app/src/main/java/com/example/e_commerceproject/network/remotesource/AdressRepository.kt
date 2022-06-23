package com.example.e_commerceproject.network.remotesource

import android.util.Log
import com.example.e_commerceproject.authentication.register.model.CustomerAddress
import retrofit2.Response

class AdressRepository(private val retrofitService: RetrofitService) {
    suspend fun postAddress(Address: CustomerAddress) : Response<CustomerAddress> {

        val n = retrofitService.updateCustomerAddress(Address)
        Log.i("TAG", "posttttcustomerAdrressssssssssssssssssssssss: ${n.body()}  ${n.code()}")
        return n
    }}