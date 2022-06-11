package com.example.e_commerceproject.homesearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceproject.homesearch.model.ProductModel
import com.example.e_commerceproject.homesearch.model.HomeSearchRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeSearchViewModel (iRepo: HomeSearchRepositoryInterface) : ViewModel() {

    private val homeRepo: HomeSearchRepositoryInterface = iRepo
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