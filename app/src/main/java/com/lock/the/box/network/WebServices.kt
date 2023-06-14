package com.lock.the.box.network

import com.lock.the.box.model.MainModel
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
    suspend fun loginApi()
}