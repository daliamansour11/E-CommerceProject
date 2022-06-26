package com.example.e_commerceproject.home.model

data class PriceRules(val price_rule: PriceRule)


data class PriceRule (
    val id: Long,

    val value_type: String,

    val value: String,

    val customer_selection: String,

    val target_type: String,

    val target_selection: String,

    val allocation_method: String,

    val allocation_limit: Any? = null,

    val once_per_customer: Boolean,

    val usage_limit: Any? = null,

    val starts_at: String,

    val ends_at: String,

    val created_at: String,

    val updated_at: String,

    val entitled_product_ids: List<Any?>,

    val entitled_variant_ids: List<Any?>,

    val entitled_collection_ids: List<Any?>,

    val entitled_country_ids: List<Any?>,

    val prerequisite_product_ids: List<Any?>,

    val prerequisite_variant_ids: List<Any?>,

    val prerequisite_collection_ids: List<Any?>,

    val customer_segment_prerequisite_ids: List<Any?>,

    val prerequisite_customer_ids: List<Any?>,

    val prerequisite_subtotal_range: Any? = null,

    val prerequisite_quantity_range: Any? = null,

    val prerequisite_shipping_price_range: Any? = null,

    val prerequisite_to_entitlement_quantity_ratio: PrerequisiteToEntitlementQuantityRatio,

    val prerequisite_to_entitlement_purchase: PrerequisiteToEntitlementPurchase,

    val title: String,

    val admin_graphql_api_id: String
)

data class PrerequisiteToEntitlementPurchase (
    val prerequisite_amount: Any? = null
)

data class PrerequisiteToEntitlementQuantityRatio (
    val prerequisite_quantity: Any? = null,

    val entitled_quantity: Any? = null
)

