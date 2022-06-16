package com.example.e_commerceproject.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.home.model.BrandModel
import com.example.e_commerceproject.home.model.HomeRepository
import com.example.e_commerceproject.home.model.HomeRepositoryInterface
import com.example.e_commerceproject.network.remotesource.CouponsRepository
import com.example.e_commerceproject.payment.model.CouponsX
import com.example.e_commerceproject.payment.model.DiscountCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response

class HomeViewModel (iRepo: HomeRepositoryInterface) : ViewModel() {
    private val homeRepo: HomeRepositoryInterface = iRepo
    //private val Repo: HomeRepository = iRepo

    val brandList = MutableLiveData<List<BrandModel>>()
    var couponsList: MutableLiveData<DiscountCode> = MutableLiveData()

    fun getAllBrands() {
        viewModelScope.launch {
            val brands = homeRepo.getAllBrands()
            withContext(Dispatchers.Main) {
                brandList.postValue(brands)
            }
        }
    }

//    fun getAllCoupons() {
//        viewModelScope.launch {
//            val response = homeRepo.getCoupons()
//            Log.i("TAG", "getAllCouponssssssssssssssssssssssssssssssssssssssssssssssss: ${response}")

//            withContext(Dispatchers.Main) {
//                if (response) {
//                  Log.i("TAG", "onViewCreated:coppppppppppppppppppppppppppppppp")
//                   couponsList.postValue(response)
////                } else {
////                    Log.i("TAG", "Error: ")
//////                              onError("Error : ${response.message()} iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ")
////
////                }
//            }
//        }

//        }
//    }
}