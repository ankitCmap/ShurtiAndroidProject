package com.lock.the.box.helper

import android.content.Context
import android.content.SharedPreferences

object BasePreferencesManager {
    const val APPLICATION_SETTINGS = "APPLICATION_SETTINGS"

    const val FB_ID = "UUID"
    const val FB_USER_NAME = "USER_NAME"
    const val FB_USER_GENDER = "USER_GENDER"
    const val FB_USER_DOB = "USER_DOB"
    const val IS_LOGIN = "IS_LOGIN"
    const val IS_SKIP = "IS_SKIP"
    const val FB_PROFILE_PATH = "FB_PROFILE_PATH"


    var prefs: SharedPreferences? = null
   /* var prefsWelCome: SharedPreferences? = null
    var prefsLanguage: SharedPreferences? = null*/

    fun initialize(context: Context) {
        prefs = context.getSharedPreferences(APPLICATION_SETTINGS, Context.MODE_PRIVATE)
    }


    @JvmStatic
    fun getString(key: String?, defaultValue: String?): String? {
        return if (prefs != null) prefs!!.getString(key, defaultValue) else defaultValue
    }


    fun getBoolean(key: String?, defVal: Boolean): Boolean {
        return if (prefs != null) prefs!!.getBoolean(key, defVal) else defVal
    }

    @JvmStatic
    fun getStringSet(key: String?, set: Set<String?>?): Set<String?>? {
        return if (prefs != null) prefs!!.getStringSet(key, set) else set
    }

    @Synchronized
    @JvmStatic
    fun putStringSet(key: String?, set: Set<String?>?) {
        if (prefs != null) prefs!!.edit().putStringSet(key, set).apply()
    }

    @Synchronized
    fun putBoolean(key: String?, value: Boolean) {
        if (prefs != null) prefs!!.edit().putBoolean(key, value).apply()
    }
    @Synchronized
    @JvmStatic
    fun putString(key: String?, value: String?) {
        if (prefs != null) prefs!!.edit().putString(key, value).apply()
    }

    @Synchronized
    @JvmStatic
    fun putInt(key: String?, value: Int) {
        if (prefs != null) prefs!!.edit().putInt(key, value).apply()
    }

    @Synchronized
    fun putFloat(key: String?, value: Float) {
        if (prefs != null) prefs!!.edit().putFloat(key, value).apply()
    }

    @Synchronized
    @JvmStatic
    fun putLong(key: String?, value: Long) {
        if (prefs != null) prefs!!.edit().putLong(key, value).apply()
    }
    fun clearPref() {
        if (prefs != null)
            prefs?.edit()?.clear()?.apply()
    }
}
