package com.example.e_commerceproject.network

import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.network.remotesource.RetrofitService

class FavoriteRepository(private val retrofitService: RetrofitService) {

  //  suspend fun postFavoriteProduct(favoriteItem: CartModel) = retrofitService.postFavorieItem(favoriteItem)

    suspend fun getFavoriteProducts() = retrofitService.getFavoriteProducts()

    suspend fun deleteFavoriteItem(id: String) = retrofitService.deleteFavoriteItem(id)

}