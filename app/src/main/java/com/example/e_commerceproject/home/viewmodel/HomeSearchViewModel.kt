package com.example.e_commerceproject.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceproject.home.model.ProductModel
import com.example.e_commerceproject.home.model.HomeRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeSearchViewModel (iRepo: HomeRepositoryInterface) : ViewModel() {

    private val homeRepo: HomeRepositoryInterface = iRepo
    val productList = MutableLiveData<List<ProductModel>>()

    fun getAllProducts(){
        viewModelScope.launch{
            val products = homeRepo.getAllProducts()
            withContext(Dispatchers.Main){
                productList.postValue(products)
            }
        }
    }
}