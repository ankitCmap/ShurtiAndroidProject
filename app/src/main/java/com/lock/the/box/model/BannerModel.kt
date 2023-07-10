package com.lock.the.box.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BannerModel(
    @Expose @SerializedName("banner_image") val banner_image: String,
    @Expose @SerializedName("device") val device: String,
    @Expose @SerializedName("img_position") val img_position: String
)