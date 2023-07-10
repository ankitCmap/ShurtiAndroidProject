package com.lock.the.box.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TestimonialModel(
    @Expose @SerializedName("comment") val comment: String,
    @Expose @SerializedName("id") val id: String,
    @Expose @SerializedName("image") val image: String,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("rating") val rating: String
)