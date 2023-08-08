package com.lock.the.box.repository

import android.util.Log
import com.lock.the.box.model.SignUpData
import com.lock.the.box.network.Resource
import com.lock.the.box.network.WebServices
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class SignUpRepository(private val webServices: WebServices) {

    suspend fun signUpRequest(hashMap: HashMap<String, Any> = HashMap<String, Any>()): Resource<SignUpData> {
        return withContext(Dispatchers.IO) {
            try {
                val response = webServices.signUpApi(hashMap)
                if (response.isSuccessful && response.body() != null) {
                    Log.d("pp_singh =>", response.body().toString())
                    Resource.success(response.body() as SignUpData)
                } else {
                    Resource.error(response.message(), null)
                }
            } catch (e: Exception) {
                Resource.error(e.message, null)

            }
        }
    }

}