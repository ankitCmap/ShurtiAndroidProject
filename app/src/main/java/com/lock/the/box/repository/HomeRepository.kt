package com.lock.the.box.repository


import com.lock.the.box.network.Result
import com.lock.the.box.model.HomeModel
import com.lock.the.box.network.WebServices
import com.lock.the.box.network.safeApiCall
import com.lock.the.box.roomdatabase.RoomDao
import javax.inject.Inject


class HomeRepository @Inject constructor(private val webServices: WebServices, private val dao: RoomDao) {
     suspend fun homeDataRequest(hashMap: HashMap<String, Any> = HashMap()): Result<HomeModel> {
        return safeApiCall { webServices.homeApi(hashMap) }
    }
}