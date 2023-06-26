package com.lock.the.box.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SignUpData(
    @Expose @SerializedName("data") val `data`: List<UserDataModel>,
    @Expose @SerializedName("message") val message: String,
    @Expose @SerializedName ("status") val status: Int
):Serializable

data class UserDataModel(
    @Expose @SerializedName("app_version")val app_version: String,
    @Expose @SerializedName("date_added")val date_added: String,
    @Expose @SerializedName("device_id")val device_id: String,
    @Expose @SerializedName("device_type")val device_type: String,
    @Expose @SerializedName("email_status")val email_status: String,
    @Expose @SerializedName("id")val id: String,
    @Expose @SerializedName("last_used_platform")val last_used_platform: String,
    @Expose @SerializedName("lat")val lat: String,
    @Expose @SerializedName("login_count")val login_count: String,
    @Expose @SerializedName("login_date")val login_date: String,
    @Expose @SerializedName("longitude")val longitude: String,
    @Expose @SerializedName("mobile_status")val mobile_status: String,
    @Expose @SerializedName("password")val password: String,
    @Expose @SerializedName("source")val source: String,
    @Expose @SerializedName("timestamp")val timestamp: String,
    @Expose @SerializedName("user_city")val user_city: String,
    @Expose @SerializedName("user_email")val user_email: String,
    @Expose @SerializedName("user_gcm")val user_gcm: Any,
    @Expose @SerializedName("user_ip")val user_ip: Any,
    @Expose @SerializedName("user_mobile")val user_mobile: String,
    @Expose @SerializedName("user_name")val user_name: String,
    @Expose @SerializedName("user_state")val user_state: String,
    @Expose @SerializedName("user_status")val user_status: String
)