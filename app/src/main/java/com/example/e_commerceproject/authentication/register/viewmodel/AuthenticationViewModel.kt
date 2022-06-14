package com.example.e_commerceproject.authentication.register.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceproject.authentication.register.model.CustomerModel
import com.example.e_commerceproject.data.AuthenticationRepository
import kotlinx.coroutines.*
import retrofit2.Response

class AuthenticationViewModel constructor( private val repository: AuthenticationRepository) : ViewModel() {


    val customerdata = MutableLiveData<CustomerModel>()
    //val customer = MutableLiveData<Post>()
    var job: Job? = null
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun postRegisterCustomer(customerModel: CustomerModel)  {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.postCustomer(customerModel)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrrkkjkj")
                    customerdata.value = response.body()

                    loading.value = false
                } else {
                    Log.i("TAG", "Errorbbbbbbbbbbbbbbssssssssssss:${response.message()} ")
                    onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
                }
            }
        }

    }



//    fun postCustomer(post: Post) {
//        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
//            val response = repository.postCustomer(post)
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrrkkjkj")
//                    customer.value = response.body()
//
//                    loading.value = false
//                } else {
//                    Log.i("TAG", "Errorbbbbbbbbbbbbbb: ")
//                    onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
//                }
//            }
//        }
//    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

}