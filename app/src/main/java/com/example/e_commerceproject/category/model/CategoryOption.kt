package com.example.e_commerceproject.category.model

import java.io.Serializable

data class Option(
    val id: Long,
    val name: String,
    val position: Int,
    val product_id: Long,
    val values: List<String>
): Serializable