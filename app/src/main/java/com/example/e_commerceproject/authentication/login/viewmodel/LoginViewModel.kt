package com.example.e_commerceproject.authentication.login.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceproject.authentication.login.model.Customerr
import com.example.e_commerceproject.authentication.login.model.Customers
import com.example.e_commerceproject.authentication.register.model.CustomerModel
import com.example.e_commerceproject.data.AuthenticationRepository
import kotlinx.coroutines.*

class LoginViewModel constructor( private val repository: AuthenticationRepository) : ViewModel() {

    val customer = MutableLiveData<Customers>()
    val customers = MutableLiveData<Customers>()
    val customeridb = MutableLiveData<Customers>()

    var job: Job? = null
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun getCustomers()  {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getCustomers()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrrkkjkj")
                    customers.value = response.body()

                    loading.value = false
                } else {
                    Log.i("TAG", "Errorbbbbbbbbbbbbbbssssssssssss:${response.message()} ")
                    //onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
                }
            }
        }

    }



    fun getCustomerById(email: String)  {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getCustomerById(email)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrrkkjkj")
                    customer.value = response.body()

                    loading.value = false
                } else {
                    Log.i("TAG", "Errorbbbbbbbbbbbbbbssssssssssss:${response.message()} ")
                    //onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
                }
            }
        }

    }


}