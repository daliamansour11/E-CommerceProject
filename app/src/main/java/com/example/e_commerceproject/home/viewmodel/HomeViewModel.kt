package com.example.e_commerceproject.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceproject.home.model.BrandModel
import com.example.e_commerceproject.home.model.HomeRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel (iRepo: HomeRepositoryInterface) : ViewModel() {

    private val homeRepo: HomeRepositoryInterface = iRepo
    val brandList = MutableLiveData<List<BrandModel>>()

    fun getAllBrands(){
        viewModelScope.launch{
            val brands = homeRepo.getAllBrands()
            withContext(Dispatchers.Main){
                brandList.postValue(brands)
            }
        }
    }
}
