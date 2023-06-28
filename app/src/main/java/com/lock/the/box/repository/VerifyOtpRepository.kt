package com.lock.the.box.repository

import android.util.Log
import com.lock.the.box.model.OtpVerifyModel
import com.lock.the.box.network.Resource
import com.lock.the.box.network.WebServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VerifyOtpRepository(private val webServices: WebServices) {

    suspend fun otpRequest(otp: HashMap<String, Any>): Resource<OtpVerifyModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = webServices.verifyOtpApi(otp)
                if (response.isSuccessful && response.body() != null) {
                    Log.d("pp_singh_1 =>", response.body().toString())
                    Resource.success(response.body() as OtpVerifyModel)
                } else {
                    Resource.error(response.message(), null)
                }
            } catch (e: Exception) {
                Resource.error(e.message, null)

            }
        }
    }
}