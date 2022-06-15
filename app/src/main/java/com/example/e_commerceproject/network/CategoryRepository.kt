package com.example.e_commerceproject.network

import com.example.e_commerceproject.network.remotesource.RetrofitService

class CategoryRepository constructor( private  val retrofitService: RetrofitService) {

    suspend fun getProductsOfCategory(id : Long) = retrofitService.getProductOfCategory(id)
    suspend fun getCategoryProducts( collection_id : String,  product_type : String , vendor : String) = retrofitService.getAllProducts(collection_id , product_type , vendor )

    suspend fun getProductsOfSubCategory(product_type : String , collection_id : Long ) = retrofitService?.getSubCategory( product_type , collection_id)
}

