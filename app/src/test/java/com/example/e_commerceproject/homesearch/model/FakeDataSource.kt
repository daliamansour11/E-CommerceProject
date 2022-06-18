package com.example.e_commerceproject.homesearch.model

import com.example.e_commerceproject.home.model.BrandImageModel
import com.example.e_commerceproject.home.model.BrandModel
import com.example.e_commerceproject.homesearch.client.HomeSearchRemoteSource
import java.util.Collections.list

class FakeDataSource():HomeSearchRemoteSource{


    private val product1 = ProductModel(1,"AdidasBag",
        ProductImageModel(1,"SourceOne",10,10), listOf(ProductVariantModel(1,"100")))

    private val product2 = ProductModel(2,"ZaraBag",
        ProductImageModel(1,"SourceTwo",10,10), listOf(ProductVariantModel(2,"200")))

    private val product3 =  ProductModel(3,"PumaBag",
        ProductImageModel(3,"SourceThree",10,10), listOf(ProductVariantModel(3,"300")))

    private val remoteProducts = listOf(product1, product2,product3)
    override suspend fun getAllProductResponse(): List<ProductModel> {
        remoteProducts.let {
            return ArrayList(it)
        }
        throw Exception("Not Products found")
    }

}