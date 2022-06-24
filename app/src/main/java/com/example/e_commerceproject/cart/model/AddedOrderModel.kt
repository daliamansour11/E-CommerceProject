package com.example.e_commerceproject.cart.model

data class AddedOrderModel(
    var email: String,
    var line_items: List<LineItem>
)
