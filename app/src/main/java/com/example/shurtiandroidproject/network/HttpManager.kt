package com.example.shurtiandroidproject.network

import android.content.Context
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object HttpManager {
    lateinit var normalClient: OkHttpClient
    private var appContext: Context? = null

    private const val TIMEOUT_IN_SEC = 30
    private const val CONNECT_TIMEOUT_IN_SEC = TIMEOUT_IN_SEC
    private const val READ_TIMEOUT_IN_SEC = TIMEOUT_IN_SEC
    private const val WRITE_TIMEOUT_IN_SEC = TIMEOUT_IN_SEC

    internal fun initialize(context: Context) {
        appContext = context
        val normalBuilder = OkHttpClient.Builder()
        normalBuilder.connectTimeout(CONNECT_TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
        normalBuilder.readTimeout(READ_TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
        normalBuilder.writeTimeout(WRITE_TIMEOUT_IN_SEC.toLong(), TimeUnit.SECONDS)
        normalClient = normalBuilder.build()
    }
}