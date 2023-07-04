package com.lock.the.box.network.api

import com.lock.the.box.model.BaseResponseData
import retrofit2.Call
import retrofit2.http.*

interface AuthApiHelper {

    @POST("login.php")
    fun doLoging(@Body modelRequest: RequestAuthModel): Call<BaseResponseData>

   }




