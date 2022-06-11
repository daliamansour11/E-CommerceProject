package com.example.e_commerceproject.details.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceproject.data.remotesource.DetailsRepository
import com.example.e_commerceproject.details.model.DetailsProductModel
import kotlinx.coroutines.*

class DetailsViewModel constructor( private val repository: DetailsRepository) : ViewModel() {

    val productInfo = MutableLiveData<DetailsProductModel>()
    var job: Job? = null
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getProductInfo(collection_id : String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getProductInf(collection_id )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrrkkjkj")
                    productInfo.postValue(response.body())

                    loading.value = false
                } else {
                    Log.i("TAG", "Errorrrrrrrrrrrrrrrr: ${response.body()} ")
                   // onError("Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")

                }
            }
        }
    }


}