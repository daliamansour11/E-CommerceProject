package com.example.e_commerceproject.cart.model


data class  DraftOrder(
    val admin_graphql_api_id: String? =null,
    val applied_discount: Any? =null,
    val billing_address: Any? =null,
    val completed_at: Any? =null,
    val created_at: String? =null,
    val currency: String? =null,
    val customer: Customer? =null,
    var email: String? =null,
    val id: String? =null,
    val invoice_sent_at: Any? =null,
    val invoice_url: String? =null,
    val line_items: List<LineItem>? =null,
    val name: String? =null,
    val note: String? =null,
    val note_attributes: List<NoteAttribute>? =null,
    val order_id: Any? =null,
    val payment_terms: Any? =null,
    val shipping_address: Any? =null,
    val shipping_line: Any? =null,
    val status: String? =null,
    val subtotal_price: String? =null,
    val tags: String? =null,
    val tax_exempt: Boolean? =null,
    val tax_lines: List<TaxLineX>? =null,
    val taxes_included: Boolean? =null,
    val total_price: String? =null,
    val total_tax: String? =null,
    val updated_at: String? =null
)