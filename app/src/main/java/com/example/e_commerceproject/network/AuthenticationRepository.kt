package com.example.e_commerceproject.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.e_commerceproject.authentication.register.model.CustomerModel
import com.example.e_commerceproject.data.remotesource.Retrofit2
import com.example.e_commerceproject.data.remotesource.RetrofitService
import retrofit2.Response

class AuthenticationRepository(private val retrofitService: RetrofitService) {

   // @RequiresApi(Build.VERSION_CODES.M)
    suspend fun postCustomer(customerModel: CustomerModel  ) : Response<CustomerModel>{
      val response = retrofitService.postCustomer(customerModel)
       Log.i("TAG", "postCustomer: ${response.code()}  ${response.body()}")
       return  response
    }

    suspend fun getCustomers() = retrofitService.getCustomers()

    suspend fun getCustomerById(email : String) = retrofitService.getCustomerById(email)

   // suspend fun postCustomer(post: Post ) = retrofitService.postCustomer(post)

}