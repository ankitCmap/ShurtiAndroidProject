package com.example.shurtiandroidproject.roomdatabase

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.helper.Utils.makeStatusBarTransparent

open class BaseActivity : AppCompatActivity(){
    var dialog: ProgressDialog? = null

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        makeStatusBarTransparent()
    }
    open fun createProgressDialog(context: Context?) {
        dialog = ProgressDialog(context)
        try {
            dialog?.show()
        } catch (ignored: WindowManager.BadTokenException) {
        }
        dialog?.setCancelable(false)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setContentView(R.layout.progress_dialog)
    }

    open fun dismissDialog() {
        if (!isFinishing) {
            dialog?.dismiss()
        }
    }
}
