package com.example.e_commerceproject.cart.model

data class LineItem(
    val admin_graphql_api_id: String?= null,
    val applied_discount: Any?= null,
    val custom: Boolean?= null,
    val fulfillment_service: String?= null,
    val gift_card: Boolean?= null,
    val grams: Int?= null,
    val id: Long?= null,
    val name: String?= null,
    var price: String?= null,
    val product_id: Long?= null,
    val properties: List<Any>?= null,
    var quantity: Int?= null,
    val requires_shipping: Boolean?= null,
    val sku: String?= null,
    val tax_lines: List<TaxLineX>?= null,
    val taxable: Boolean?= null,
    val title: String?= null,
    val variant_id: Long?= null,
    val variant_title: String?= null,
    val vendor: String?= null
)