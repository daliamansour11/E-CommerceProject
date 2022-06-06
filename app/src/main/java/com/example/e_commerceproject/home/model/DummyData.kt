package com.example.e_commerceproject.home.model

import com.example.e_commerceproject.R

class DummyData {
    companion object {
        var BRAND_DATA: ArrayList<DummyBrandModel> = arrayListOf<DummyBrandModel>(
            DummyBrandModel("Zara", R.drawable.dummyproductimage),
            DummyBrandModel("Adidas", R.drawable.dummyproductimage),
            DummyBrandModel("Polo", R.drawable.dummyproductimage),
            DummyBrandModel("Puma", R.drawable.dummyproductimage)
        )
        var PRODUCT_DATA:ArrayList<DummyProductModel> =arrayListOf<DummyProductModel>(
            DummyProductModel("",R.drawable.dummyproductimage,"",""),
            DummyProductModel("",R.drawable.dummyproductimage,"",""),
            DummyProductModel("",R.drawable.dummyproductimage,"","")
        )
    }
}