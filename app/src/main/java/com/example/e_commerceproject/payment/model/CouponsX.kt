package com.example.e_commerceproject.payment.model

data class CouponsX(
    val discount_codes: List<DiscountCode>
)

data class CouponsModel(
    var discount_code :DiscountCode
)