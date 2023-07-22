package com.lock.the.box.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HomeModel(
    @Expose @SerializedName("banner")val banner: List<BannerModel>,
    @Expose @SerializedName("message")val message: String,
    @Expose @SerializedName("status")val status: Int,
    @Expose @SerializedName("store_wise_product")val store_wise_product: List<StoreWiseProductModel>,
    @Expose @SerializedName("testimonial")val testimonial: List<TestimonialModel>
): Serializable