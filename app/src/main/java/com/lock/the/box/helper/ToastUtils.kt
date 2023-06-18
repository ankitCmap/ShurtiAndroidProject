package com.lock.the.box.helper

import android.content.Context
import android.widget.Toast

object ToastUtils {

    fun showToast(context: Context?, rID: Int, length: Int) {
        Toast.makeText(context, rID, length).show()
    }

    fun showShortToast(rID: Int) {
    }

    fun showToast(context: Context?, rID: String?, length: Int) {
        Toast.makeText(context, rID, length).show()

    }


    fun showLongToast(rID: String?) {
    }

    fun showShortToast(rID: String?) { /* compiled code */
    }

}