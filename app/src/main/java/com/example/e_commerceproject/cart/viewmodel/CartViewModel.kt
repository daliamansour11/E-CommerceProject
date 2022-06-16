package com.example.e_commerceproject.cart.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.network.remotesource.CartRepository
import kotlinx.coroutines.*

class CartViewModel(private val repo: CartRepository): ViewModel()  {

    var cart_Response : MutableLiveData<CartModel> = MutableLiveData()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // onError("Exception handled: ${throwable.localizedMessage}")
    }
        fun getCart() {
            job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = repo.getCartItem()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.i("TAG", "onViewCreated:rrrrrrrrrrrrkkjkj")
                        cart_Response.postValue(response.body())
                        loading.value = false
                    } else {
                        Log.i("TAG", "Error: ")
//                             onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
//
                    }
                }
            }
        }
    fun updateCart(draftOrder: DraftOrder) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.updatedcartItem(draftOrder)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onViewCreated:rrrrrrrrgggggggrrrrkkjkj")
                    cart_Response.postValue(response.body())
                    loading.value = false
                } else {
                    Log.i("TAG", "Error: ")
//                              onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")

                }
            }
        }

    }

    }