package com.example.e_commerceproject.network.remotesource
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.e_commerceproject.cart.model.CartListModel
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
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
    suspend fun updatedcartItem(cart_id :String,cartitem: CartModel): Response<CartModel> {
        val cartupdated = retrofitService.updateCartOrder(cart_id,cartitem)
        println("--------------------Update-------------------")
        Log.i("TAG", "updatepostitem: ${cartupdated.body()}  ${cartupdated.code()}")
        println("----------HHHH--------------HHHHHHH---")
        return  cartupdated
}
//    suspend fun deleteCartItem(cart_id :String): Response<CartModel> {
//        val cartupdated = retrofitService.deleteDraftOrder(cart_id)
//        println("--------------------delete-------------------")
//        Log.i("TAG", "deletepostitem: ${cartupdated.body()}  ${cartupdated.code()}")
//        println("----------dddddddddd--------------ddddddddddddd---")
//        return  cartupdated
//
//
//    }
    suspend fun deleteCartItems(id: String) = retrofitService.deleteCartItem(id)

}