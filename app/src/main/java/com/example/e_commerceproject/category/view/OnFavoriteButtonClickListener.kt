package com.example.e_commerceproject.category.view

import com.example.e_commerceproject.category.model.Product

interface OnFavoriteButtonClickListener {
    fun OnFavoriteButtonClickListener(data: Product)
    fun OnRemoveFromFavoriteButtonClickListener(data: Product)

}