package com.lock.the.box

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.lock.the.box.adapter.helper.BasePreferencesManager
import com.lock.the.box.network.HttpManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShurtiApplication : Application() {
    var mInstance: ShurtiApplication? = null
    var mContext: Context? = null

    var s:Int?=0
    var m:Int?=0
    var l:Int?=0

    init {
        instance = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
         var instance: ShurtiApplication? = null


    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mContext = this
        HttpManager.initialize(this)
        BasePreferencesManager.initialize(this)
    }
}