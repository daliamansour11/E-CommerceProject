package com.example.e_commerceproject.category.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerceproject.category.model.CategoryModel
import com.example.e_commerceproject.category.model.Product
import com.example.e_commerceproject.data.CategoryRepository
import kotlinx.coroutines.*

class CategoryViewModel constructor( private val repository: CategoryRepository) : ViewModel() {


    val categoryList = MutableLiveData<CategoryModel>()
    var subCategoryList = MutableLiveData<CategoryModel>()
    var job: Job? = null
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
       // onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun getCategoryProduct(collection_id : String,  product_type : String , vendor : String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getCategoryProducts(collection_id , product_type , vendor)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.i("TAG", "onViewCreated:rrrrrrrrrrrrkkjkj")
                    categoryList.postValue(response.body())

                    loading.value = false
                } else {
                    Log.i("TAG", "Error: ")
                    onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")

                }
            }
        }

    }


    var _response = MutableLiveData<CategoryModel>()
    fun getProductOfCategory(id : Long) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getProductsOfCategory(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    categoryList.postValue(response.body())
                     loading.value = false
                } else {
                  //  onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")

                }
            }
        }

    }

    fun getProductOfSubCategory(product_type : String , collection_id : Long) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getProductsOfSubCategory(product_type, collection_id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    subCategoryList.postValue(response.body())
                    loading.value = false
                } else {
                   // onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }


}