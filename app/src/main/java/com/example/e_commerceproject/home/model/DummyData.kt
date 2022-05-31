package com.example.e_commerceproject.home.model

import com.example.e_commerceproject.R

class DummyData {
    companion object {
        var BRAND_DATA: ArrayList<BrandModel> = arrayListOf<BrandModel>(
            BrandModel("Zara", R.drawable.dummyproductimage),
            BrandModel("Adidas", R.drawable.dummyproductimage),
            BrandModel("Polo", R.drawable.dummyproductimage),
            BrandModel("Puma", R.drawable.dummyproductimage)
        )
        var PRODUCT_DATA:ArrayList<ProductModel> =arrayListOf<ProductModel>(
            ProductModel("",R.drawable.dummyproductimage,"",""),
            ProductModel("",R.drawable.dummyproductimage,"",""),
            ProductModel("",R.drawable.dummyproductimage,"","")
        )
    }
}