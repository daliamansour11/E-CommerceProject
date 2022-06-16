package com.example.e_commerceproject.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceproject.network.remotesource.CouponsRepository
import com.example.e_commerceproject.payment.model.CouponsX
import com.example.e_commerceproject.payment.model.DiscountCode
import io.reactivex.rxjava3.plugins.RxJavaPlugins.onError
import kotlinx.coroutines.*

class CouponsViewModel (private val repo: CouponsRepository): ViewModel() {

    var coupons_Response = MutableLiveData<CouponsX>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // onError("Exception handled: ${throwable.localizedMessage}")
    }
    fun getCoupon() {


        Log.i("TAG", "getCoupon: -----------------------------------------------")
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.getCoupons()
            Log.i("TAG", "getCoupon: -----------------------------------------------")

            withContext(Dispatchers.Main) {
            //    if (response.isSucessful) {
                    Log.i("TAG", "onViewCreated:couponssssssssssssssssssssssssssssssssssssssssssssssss")
                coupons_Response.value
                    loading.value = false
              //  } else {
                    Log.i("TAG", "Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr: ")
                           //  onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
//
                }
            }
        }
    }
