package com.example.e_commerceproject.cart.model

data class CartListModel(
    val draft_orders: List<DraftOrder>
)

data class CartModel(
    val draft_order: DraftOrder? =null
)