package com.example.shurtiandroidproject.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.helper.BasePreferencesManager
import com.example.shurtiandroidproject.roomdatabase.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch {
            delay(3000L)
            if (BasePreferencesManager.getBoolean(BasePreferencesManager.IS_LOGIN, false)
                ||BasePreferencesManager.getBoolean(BasePreferencesManager.IS_SKIP, false)) {
                val intent = Intent(this@SplashActivity, CartActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                val intent = Intent(this@SplashActivity, LoginSignupActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}