package com.example.e_commerceproject.address.view.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceproject.authentication.register.model.CustomerAddress
import com.example.e_commerceproject.network.remotesource.AdressRepository
import kotlinx.coroutines.*

class AddressViewModel  (private var repo : AdressRepository): ViewModel(){
    var  mCustomerAddress  = MutableLiveData<CustomerAddress>()
    private var address_response : LiveData<CustomerAddress> =mCustomerAddress
    var job: Job? = null
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // onError("Exception handled: ${throwable.localizedMessage}")
    }
    fun pushPostAddress(address: CustomerAddress) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.postAddress(address)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "pushPostAddress: addresssssssssssssssssssssssss")
                    mCustomerAddress.postValue(response.body())
                    loading.value = false
                } else {
                    Log.i("TAG", "Errorrrrrrrrrrrrrrrr: ${response.body()} ")
                    // onError("Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")

                }
            }
        }
    }

}