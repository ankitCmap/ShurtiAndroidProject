package com.lock.the.box.network

import com.lock.the.box.model.MainModel
import com.lock.the.box.model.OtpVerifyModel
import com.lock.the.box.model.SignUpData
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface WebServices {
    @GET("search.php?s")
    suspend fun dalaList(): Response<MainModel>

    @GET("search.php")
    suspend fun searchByProducdName(@Query("s") key: String): Response<MainModel>

    @GET("search.php")
    suspend fun searchByProducdId(@Query("i") key: String): Response<MainModel>

    @POST("login.php")
    suspend fun loginApi(@Body params: JSONObject): Response<SignUpData>

    @POST("sign_up.php")
    suspend fun signUpApi(@Body params: HashMap<String, Any>): Response<SignUpData>

    @POST("check-otp.php")
    suspend fun verifyOtpApi(@Body params: HashMap<String, Any>): Response<OtpVerifyModel>
}