package com.lock.the.box.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OtpVerifyModel(
    @Expose @SerializedName("data")val data: String,
    @Expose @SerializedName("message")val message: String,
    @Expose @SerializedName("token")val token: String,
    @Expose @SerializedName("status")val status: String,
    @Expose @SerializedName("code")val code: String
): Serializable