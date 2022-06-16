package com.example.e_commerceproject.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
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
    var _coupons: LiveData<CouponsX> = coupons_Response

    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // onError("Exception handled: ${throwable.localizedMessage}")
    }
    fun getCoupon() {

        viewModelScope.launch( ) {
            val response = repo.getCoupons()
            coupons_Response.postValue(response.body())
                         Log.i("TAG", "onViewCreated:couponssssssssssssssssssssssssssssssssssssssssssssssss")

        }
//        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

//            val response = repo.getCoupons()
//            Log.i("TAG", "${response}")
//
//            withContext(Dispatchers.Main) {
//             //   if (response.isSucessful) {
//                coupons_Response.postValue(response)
//                    loading.value = false
//              //  } else {
                           //  onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
//
                }
            }
