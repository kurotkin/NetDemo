package com.kurotkin.netdemo.data.products

import com.google.gson.annotations.SerializedName

data class ProductCart(
    @SerializedName("limit")
    val limit: Int,

    val products: List<Product>,
    val skip: Int,
    val total: Int
)