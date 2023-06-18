package com.lock.the.box.model

import java.io.Serializable

open class BaseResponseData : Serializable {
    /**
     * {
     * "status": 1,
     * "user_id": "21",
     * "message": "_user_login_success"
     * }
     */
    var status: String? = null
        protected set

    //TODO change apis response messages check and messages will change
    var message: String? = null
        protected set
    var failed_login: Boolean? = null
        protected set
    var params: BasePageParams? = null
        protected set
}