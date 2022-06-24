package com.example.e_commerceproject.currencyConverter.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceproject.currencyConverter.model.ConverterModel
import com.example.e_commerceproject.network.ConverterRepoInterface
import com.example.e_commerceproject.network.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConverterViewModel(val  iRepo_convert : ConverterRepository) : ViewModel(){
    val _Convert_Response = MutableLiveData<ConverterModel>()
    fun getcontvertedResponse( api : String,to :String, amount:String,from :String){

        viewModelScope.launch {
            val convert_value = iRepo_convert.getCurrency(api,to,amount,from)
            withContext(Dispatchers.Main){
                _Convert_Response.postValue(convert_value.body())

                Log.i("\n\n viewModel","---------------------------"+convert_value+"\n\n")
            }
        }
    }
}

