package com.lock.the.box.network.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lock.the.box.model.BaseResponseData

open class UserModel : BaseResponseData() {
    @SerializedName("id")
    @Expose
    var id: String = ""
    @SerializedName("user_id")
    @Expose
    var user_id: String? = null
    @SerializedName("instagram_username")
    @Expose
    var instagram_username: String? = null
    @SerializedName("instagram_follower_count")
    @Expose
    var instagram_follower_count: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null






}