package com.lock.the.box.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoreWiseProductModel(
    @Expose @SerializedName("id") val id: String,
    @Expose @SerializedName("product_list") val product_list: List<List<ProductModel>>,
    @Expose @SerializedName("store_name") val store_name: String
)