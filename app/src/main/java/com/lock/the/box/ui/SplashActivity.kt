package com.lock.the.box.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.lock.the.box.R
import com.lock.the.box.adapter.helper.BasePreferencesManager
import com.lock.the.box.roomdatabase.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch {
            delay(3000L)
            if (BasePreferencesManager.getBoolean(BasePreferencesManager.IS_LOGIN, false)
                || BasePreferencesManager.getBoolean(BasePreferencesManager.IS_SKIP, false)) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
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