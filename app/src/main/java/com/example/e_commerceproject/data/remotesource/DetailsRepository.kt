package com.example.e_commerceproject.data.remotesource

class DetailsRepository ( private  val retrofitService: RetrofitService) {
    suspend fun getProductInf(product_id: String) = retrofitService.getProductInfo(product_id)

}