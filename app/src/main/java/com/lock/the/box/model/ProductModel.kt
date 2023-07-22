package com.lock.the.box.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductModel(
    @Expose @SerializedName("author_name")val author_name: String,
    @Expose @SerializedName("bcin")val bcin: String,
    @Expose @SerializedName("bookchor_image")val bookchor_image: String,
    @Expose @SerializedName("cat_id")val cat_id: String,
    @Expose @SerializedName("condition_id")val condition_id: String,
    @Expose @SerializedName("goodreads_avg_rating")val goodreads_avg_rating: String,
    @Expose @SerializedName("isbn")val isbn: String,
    @Expose @SerializedName("isbn_10")val isbn_10: String,
    @Expose @SerializedName("item_id")val item_id: String,
    @Expose @SerializedName("product_id")val product_id: String,
    @Expose @SerializedName("title")val title: String
): Serializable