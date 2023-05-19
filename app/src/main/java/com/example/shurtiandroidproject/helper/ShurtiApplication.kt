package com.example.shurtiandroidproject.helper

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.shurtiandroidproject.network.HttpManager

class ShurtiApplication : Application() {
    var mInstance: ShurtiApplication? = null
    var mContext: Context? = null

    var s:Int?=null
    var m:Int?=null
    var l:Int?=null

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