package com.lock.the.box.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OtpVerifyModel(
    @Expose @SerializedName("data")val `data`: Data,
    @Expose @SerializedName("message")val message: String,
    @Expose @SerializedName("token")val token: String
)