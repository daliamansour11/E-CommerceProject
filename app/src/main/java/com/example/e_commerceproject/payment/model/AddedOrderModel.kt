package com.example.e_commerceproject.payment.model

import com.example.e_commerceproject.cart.model.LineItem

data class AddedOrderModel(
    var email: String,
    var line_items: List<LineItem>
)
