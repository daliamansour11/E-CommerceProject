package com.example.e_commerceproject.payment.model

data class DiscountCode(
    val code: String,
    val created_at: String,
    val id: Int,
    val price_rule_id: Int,
    val updated_at: String,
    val usage_count: Int
)