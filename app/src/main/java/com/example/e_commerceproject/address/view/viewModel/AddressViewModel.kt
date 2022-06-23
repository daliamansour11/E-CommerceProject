package com.example.e_commerceproject.address.view.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceproject.authentication.register.model.CustomerAddress
import com.example.e_commerceproject.authentication.register.model.CustomerModel
import com.example.e_commerceproject.authentication.register.model.GetAddress
import com.example.e_commerceproject.authentication.register.model.PostAddress
import com.example.e_commerceproject.network.remotesource.AdressRepository
import kotlinx.coroutines.*

class AddressViewModel  (private var repo : AdressRepository): ViewModel() {
    var postCustomerAddress = MutableLiveData<PostAddress>()
    var getCustomerAddresses = MutableLiveData<GetAddress>()
    var address_response: LiveData<PostAddress> = postCustomerAddress
    var job: Job? = null
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun pushPostAddress(id: String, address: PostAddress) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.postAddress(id, address)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "getPostAddress: addresssssssssssssssssssssssss")
                    postCustomerAddress.value = response.body()
                    loading.value = false
                } else {
                    Log.i("TAG", "Errorrrrrrrrrrrrrrrr: ${response.body()} ")
                    // onError("Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")

                }
            }

        }
    }
//                if (response.isSuccessful) {
//                    mCustomerAddress.postValue(response.body())
//                    Log.i("TAGGGG", "pushPostAddress: addresssssssssssssssssssssssss${response.code()}")
//
//                    loading.value = false
//                } else {
//                    Log.i("TAGGGG", "Errorrrrrrrrrrrrrrrr: ${response.body()} ")
//                    // onError("Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
//
//                }
//            }
//


    fun getAddress(customer_id: String) {
         job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.getAddress(customer_id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "getPostAddress: addresssssssssssssssssssssssss")
                    getCustomerAddresses.postValue(response.body())
                    loading.value = false
                } else {
                    Log.i("TAG", "Errorrrrrrrrrrrrrrrr: ${response.body()} ")
                    // onError("Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")

                }
            }
        }
    }
}
