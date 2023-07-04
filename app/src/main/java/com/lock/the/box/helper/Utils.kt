package com.lock.the.box.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.util.*
import kotlin.concurrent.schedule

object Utils {

    @SuppressLint("NewApi")
    fun Activity.makeStatusBarTransparent() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


    fun hideSoftKeyboard(context: Activity?) {
        Timer("SettingUp", false).schedule(300) {
            if (context?.currentFocus != null) {
                val inputMethodManager =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(context.currentFocus!!.windowToken, 0)
            }
        }
    }
    fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    fun checkInternet(context: Context): Boolean {
        return checkForInternet(context)
    }
    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

    }
    /*--------Encrypted Function---------*/
    fun encryptedValue(input: String): String {
        val inputLen = input.length
        val randKey = (Math.random() * 9 + 1).toInt()
        val inputChr = IntArray(inputLen)
        for (i in 0 until inputLen) {
            inputChr[i] = input[i].code - randKey
        }
        val sb = StringBuilder()
        for (i in inputChr) {
            sb.append(i).append("a")
        }
        sb.append(randKey.toString()[0].code + 50)
        return sb.toString()
    }

    /*-------Decrypted Function--------*/
    fun decryptedValue(input: String): String {
        val inputArr = input.split("a".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val inputLen = inputArr.size - 1
        // int randKey = (int) inputArr[inputLen].charAt(0) - 50;
        val `val` = inputArr[inputLen].toInt() - 50
        val randKey = `val`.toChar().toString()
        val inputChr = IntArray(inputLen)
        for (i in 0 until inputLen) {
            inputChr[i] = inputArr[i].toInt() + Integer.valueOf(randKey)
        }
        val sb = StringBuilder()
        for (i in inputChr) {
            sb.append(i.toChar())
        }
        return sb.toString()
    }

}