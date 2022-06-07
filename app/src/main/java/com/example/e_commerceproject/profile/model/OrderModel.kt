package com.example.e_commerceproject.profile.model

data class OrderModel(
    var id: Long,
    var total_price: String,
    var created_at: String,
    var customer: CustomerModel,
    var line_items: List<LineItemModel>
)
