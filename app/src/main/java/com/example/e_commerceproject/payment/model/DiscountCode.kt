package com.example.e_commerceproject.payment.model

data class DiscountCode(
    val code: String?=null,
    val created_at: String?=null,
    val id: Long?=null,
    val price_rule_id: Long?=null,
    val updated_at: String?=null,
    val usage_count: Int?=null
)


/*

{
    "discount_codes": [
    {
        "id": 507328175,
        "price_rule_id": 507328175,
        "code": "SUMMERSALE10OFF",
        "usage_count": 0,
        "created_at": "2022-05-30T21:04:52-04:00",
        "updated_at": "2022-05-30T21:04:52-04:00"
    }
    ]
}*/
