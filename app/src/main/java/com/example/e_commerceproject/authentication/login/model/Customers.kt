package com.example.e_commerceproject.authentication.login.model

data class Customers (val customers: List<Customerr>)

data class Customerr (
    val id: Long,
    val email: String,

    val accepts_marketing: Boolean,

    val created_at: String,

    val updated_at: String,

    val first_name: String,

    val last_name: String,

    val orders_count: Long,

    val state: String,

    val total_spent: String,

    val last_order_id: Any? = null,

    val note: Any? = null,

    val verified_email: Boolean,

    val multipass_identifier: Any? = null,

    val tax_exempt: Boolean,

    val phone: String,
    val tags: String,

    val last_order_name: Any? = null,

    val currency: String,
    val addresses: List<Address>,

    val accepts_marketing_updated_at: String,

    val marketing_opt_in_level: Any? = null,

    val tax_exemptions: List<Any?>,

    val email_marketing_consent: MarketingConsent,

    val sms_marketing_consent: MarketingConsent,

    val admin_graphql_api_id: String,

    val default_address: Address
)

data class Address (
    val id: Long,

    val customer_id: Long,

    val first_name: String,

    val last_name: String,

    val company: Any? = null,
    val address1: String,
    val address2: Any? = null,
    val city: String,
    val province: String,
    val country: String,
    val zip: String,
    val phone: String,
    val name: String,

    val province_code: Any? = null,

    val country_code: String,

    val country_name: String,

    val default: Boolean
)

data class MarketingConsent (
    val state: String,

    val opt_in_level: String,

    val consent_updated_at: Any? = null,

    val consent_collected_from: String? = null
)

