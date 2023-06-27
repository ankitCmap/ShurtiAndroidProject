package com.lock.the.box.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataNew(
    @Expose @SerializedName("user_id")val user_id : String
): Serializable