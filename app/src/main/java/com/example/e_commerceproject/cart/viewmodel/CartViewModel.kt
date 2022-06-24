package com.example.e_commerceproject.cart.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceproject.cart.model.CartListModel
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.currencyConverter.model.ConverterModel
import com.example.e_commerceproject.network.ConverterRepository
import com.example.e_commerceproject.network.remotesource.CartRepository
import kotlinx.coroutines.*
class CartViewModel(private val repo: CartRepository ): ViewModel()  { //,val iRepo_convert :ConverterRepository

    var cart_Response : MutableLiveData<CartListModel> = MutableLiveData()
    var updatecart_Res : MutableLiveData<CartModel> = MutableLiveData()
    var deleteCart_Res : MutableLiveData<CartModel> = MutableLiveData()
    val _Convert_Response = MutableLiveData<ConverterModel>()

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
                    Log.i("TAG", "Error: ${response.code()} ")
//                             onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
//
                }
            }
        }
    }
    fun updateCart(cart_id :String,cart: CartModel) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.updatedcartItem(cart_id,cart)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onViewCreated:rrrrrrrrgggggggrrrrkkjkj")
                    updatecart_Res.postValue(response.body())
                    loading.value = false
                } else {
                    Log.i("TAG", "Error: ")
//                              onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")

                }
            }
        }

    }

//    fun deleteCart(cart_id: String) {
//        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
//            val response = repo.deleteCartItem(cart_id)
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    Log.i("TAG", "onViewCreated:rrrrrrrrgggggggrrrrkkjkj")
//                    updatecart_Res.postValue(response.body())
//                    loading.value = false
//                } else {
//                    Log.i("TAG", "Error: ")
////                              onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
//
//                }
//            }
//        }
//
//    }
    fun deleteCartProduct(id : String)  {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.deleteCartItems(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrrkkjkj")
                    deleteCart_Res .value = response.body()

                    loading.value = false
                } else {
                    Log.i("TAG", "Errorbbbbbbbbbbbbbbssssssssssss:${response.message()} ")
//                    onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
                }
            }
        }

    }


//    fun getcontvertedResponse( api : String,to :String, amount:String,from :String){
//
//        viewModelScope.launch {
//            val convert_value = iRepo_convert.getCurrency(api,to,amount,from)
//            withContext(Dispatchers.Main){
//                _Convert_Response.postValue(convert_value.body())
//
//                Log.i("\n\n viewModel","---------------------------"+convert_value+"\n\n")
//            }
//        }
//    }


//      fun getTotalPrice(): LiveData<Double> {
//         return  repo.getTotalPrice()

//    }

}