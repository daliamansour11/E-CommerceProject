package com.example.e_commerceproject.network.remotesource
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.e_commerceproject.cart.model.CartListModel
import com.example.e_commerceproject.cart.model.CartModel
import retrofit2.Response
class CartRepository(private val retrofitService: RetrofitService) {

    private  var itemPrice = MutableLiveData<Double>()
    suspend fun getCartItem(): Response<CartListModel> {
        val cart = retrofitService.getpostedOrder()
        println("------------------getttt---------------------")

        Log.i("TAG", "getpostitem: ${cart.body()}  ${cart.code()}")
        println("--------------------getttt-------------------")
        return cart
    }

    suspend fun updatedcartItem(cartitem: CartModel): Response<CartModel> {
        val cartupdated = retrofitService.updateCartOrder(cartitem)
        println("--------------------Update-------------------")
        Log.i("TAG", "updatepostitem: ${cartupdated.body()}  ${cartupdated.code()}")
        println("----------HHHH--------------HHHHHHH---")
        return  cartupdated

}}