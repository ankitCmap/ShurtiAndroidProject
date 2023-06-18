package com.lock.the.box.network.api



import com.lock.the.box.model.BaseResponseData
import retrofit2.Call
import retrofit2.http.*


interface AuthApiHelper {


    @POST("login.php")
    fun doLoging(@Body modelRequest: RequestAuthModel): Call<BaseResponseData>

    @POST("check-otp.php")
    fun checkOTP(@Body modelRequest: RequestAuthModel): Call<BaseResponseData>


    @POST("api/ForgetPassword")
    fun doForgotpassword(
        @Body signUpRequestData: RequestAuthModel, @Header("content-type")
        contentType: String, @Header("Authorization") Authorization: String
    ): Call<ResponceAuthModel>

    @PUT("/api/ForgetPassword/{user_id}")
    fun doconfirmpassword(
        @Path("user_id") User_id: String, @Body signUpRequestData: RequestAuthModel, @Header("content-type")
        contentType: String, @Header("Authorization") Authorization: String
    ): Call<ResponceAuthModel>


    @POST("api/GoogleLogin")
    fun doGoogleLogin(
        @Body signUpRequestData: RequestAuthModel, @Header("content-type")
        contentType: String, @Header("Authorization") Authorization: String
    ): Call<ResponceAuthModel>


    @POST("api/FacebookLogin")
    fun doFaceBookLogin(
        @Body signUpRequestData: RequestAuthModel, @Header("content-type")
        contentType: String, @Header("Authorization") Authorization: String
    ): Call<ResponceAuthModel>

/*    @POST(ServerUrls.Follow)
    fun dofollow(
        @Body followDataModel: FollowDataModel?, @Header("content-type")
        contentType: String, @Header("Authorization") Authorization: String
    ): Call<FollowDataModel>*/

   }




