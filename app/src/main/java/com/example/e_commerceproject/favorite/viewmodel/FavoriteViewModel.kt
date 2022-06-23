package com.example.e_commerceproject.favorite.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceproject.cart.model.CartListModel
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.network.FavoriteRepository
import kotlinx.coroutines.*

class FavoriteViewModel constructor( private val repository: FavoriteRepository) : ViewModel() {


    val favoriteProducts = MutableLiveData<CartListModel>()
    var deleteFromFavorite = MutableLiveData<CartModel>()
    var job: Job? = null
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun getFavoriteProducts()  {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getFavoriteProducts()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrrkkjkj")
                    favoriteProducts.value = response.body()

                    loading.value = false
                } else {
                    Log.i("TAG", "Errorbbbbbbbbbbbbbbssssssssssss:${response.message()} ")
                    onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
                }
            }
        }

    }

    fun deleteFavoriteProduct(id : String)  {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.deleteFavoriteItem(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrrkkjkj")
                    deleteFromFavorite.value = response.body()

                    loading.value = false
                } else {
                    Log.i("TAG", "Errorbbbbbbbbbbbbbbssssssssssss:${response.message()} ")
                    onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

}