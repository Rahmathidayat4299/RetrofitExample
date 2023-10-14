package com.example.retrofitexample.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

//bagian dalam json or list json or data object json
@Keep
data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val desc: String,
    @SerializedName("price")
    val price: Long,
    @SerializedName("images")
    val images: List<String>,
)

//format terluar json formatting
@Keep
data class ProductResponse(
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int,

    )
