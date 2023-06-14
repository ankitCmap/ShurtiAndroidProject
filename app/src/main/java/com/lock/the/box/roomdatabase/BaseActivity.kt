package com.lock.the.box.roomdatabase

import android.app.ProgressDialog
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.lock.the.box.helper.Utils.makeStatusBarTransparent

open class BaseActivity : AppCompatActivity(){
    var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        makeStatusBarTransparent()
    }
   /* open fun createProgressDialog(context: Context?) {
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
    }*/
}
