package com.lock.the.box.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BasePageParams : Serializable {
    @SerializedName("items_per_page")
    var itemsPerPage = 0
    var totalItems = 0
    var status: String? = null
    var page = 0

}