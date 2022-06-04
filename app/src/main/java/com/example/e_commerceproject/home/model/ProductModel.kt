package com.example.e_commerceproject.home.model

data class ProductModel(
    var id : Long,
    var title: String,
    var image: ProductImageModel,
    var variants: List<ProductVariantModel>
)
