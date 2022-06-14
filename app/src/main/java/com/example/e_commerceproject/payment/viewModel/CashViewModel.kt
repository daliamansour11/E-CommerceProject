package com.example.e_commerceproject.payment.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceproject.network.remotesource.CouponsRepository
import com.example.e_commerceproject.payment.model.CouponsX
import kotlinx.coroutines.*

class CashViewModel(private val discount_repo:CouponsRepository):ViewModel() {

    var myCoupons= MutableLiveData<CouponsX>()
    var job: Job? = null
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // onError("Exception handled: ${throwable.localizedMessage}")
    }
    fun getMyCoupons() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = discount_repo.getCoupons( )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "cashhhhhhhhhhhhhhh:rrrrrrrrrrrrkkjkj")
                    myCoupons.postValue(response.body())

                    loading.value = false
                } else {
                    Log.i("TAG", "Errorrrrrrrrrrrrrrrr coupons: ${response.body()} ")
                    // onError("Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")

                }
            }
        }
    }


}