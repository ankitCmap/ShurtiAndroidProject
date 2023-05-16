package com.example.shurtiandroidproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.databinding.ActivityLoginSignupBinding
import com.example.shurtiandroidproject.helper.BasePreferencesManager
import com.example.shurtiandroidproject.roomdatabase.BaseActivity

class LoginSignupActivity :BaseActivity() {

    lateinit var binding:ActivityLoginSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_login_signup)
        initView()
    }

    private fun initView() {
        binding.tvskip.setOnClickListener {
            BasePreferencesManager.putBoolean(BasePreferencesManager.IS_SKIP,true)
            val intent = Intent(this@LoginSignupActivity, NewMainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}