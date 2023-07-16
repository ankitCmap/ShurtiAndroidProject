package com.lock.the.box.repository

import android.util.Log
import com.lock.the.box.model.HomeModel
import com.lock.the.box.model.SignUpData
import com.lock.the.box.network.Resource
import com.lock.the.box.network.WebServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val webServices: WebServices) {
    suspend fun homeDataRequest(hashMap: HashMap<String, Any> = HashMap<String, Any>()): Resource<HomeModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = webServices.homeApi(hashMap)
                if (response.isSuccessful && response.body() != null) {
                    Log.d("pp_singh =>", response.body().toString())
                    Resource.success(response.body() as HomeModel)
                } else {
                    Resource.error(response.message(), null)
                }
            } catch (e: Exception) {
                Resource.error(e.message, null)

            }
        }
    }
}