package com.example.e_commerceproject.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.home.model.PriceRules
import com.example.e_commerceproject.network.remotesource.CouponsRepository
import com.example.e_commerceproject.payment.model.CouponsX
import kotlinx.coroutines.*

class CouponsViewModel(private val repo: CouponsRepository) : ViewModel() {
    var coupons_Response = MutableLiveData<CouponsX>()
    var validate_Response = MutableLiveData<CouponsX>()
    var priceRulesValue = MutableLiveData<PriceRules>()
    var _coupons: LiveData<CouponsX> = coupons_Response

    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // onError("Exception handled: ${throwable.localizedMessage}")
    }

//    fun getCoupon() {
//
//        viewModelScope.launch() {
//            val response = repo.getCoupons()
//            coupons_Response.postValue(response.body())
//            Log.i("TAG", "onViewCreated:couponssssssssssssssssssssssssssssssssssssssssssssssss")
//
//        }

    fun getCoupon() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

            val response = repo.getCoupons()
            Log.i("TAG", "${response}")

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "getCoupon: coponsssssssssssssssssssss")
                    coupons_Response.postValue(response.body())
                    loading.value = false
                } else {
                    Log.i("TAG", "getCoupon: Errorrrrrrrrrrrrrrcoponsssssssssssssssssssss")


                }
            }

        }
    }

    fun validateCoupons() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

            val response = repo.getCoupons()
            Log.i("TAG", "${response}")

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "getCoupon: coponsssssssssssssssssssss")
                    validate_Response.postValue(response.body())
                    loading.value = false
                } else {
                    Log.i("TAG", "getCoupon: Errorrrrrrrrrrrrrrcoponsssssssssssssssssssss")


                }
            }

        }
    }

    fun getPriceRuleDiscountValue(id: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.getPriceRuleDiscountValue(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrrkkjkj")
                    priceRulesValue.value = response.body()

                    loading.value = false
                } else {
                    Log.i("TAG", "Errorbbbbbbbbbbbbbbssssssssssss:${response.message()} ")
                }
            }
        }

    }


}

//