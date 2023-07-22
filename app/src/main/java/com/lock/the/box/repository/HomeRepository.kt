package com.lock.the.box.repository


import com.lock.the.box.network.Result
import com.lock.the.box.model.HomeModel
import com.lock.the.box.network.WebServices
import com.lock.the.box.network.safeApiCall


class HomeRepository(private val webServices: WebServices) {
     suspend fun homeDataRequest(hashMap: HashMap<String, Any> = HashMap()): Result<HomeModel> {
        return safeApiCall { webServices.homeApi(hashMap) }
    }
}