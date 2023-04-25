package com.example.shurtiandroidproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.databinding.ActivityChangePasswordBinding
import com.example.shurtiandroidproject.databinding.ActivityLoginSignupBinding
import com.example.shurtiandroidproject.helper.BasePreferencesManager
import com.example.shurtiandroidproject.roomdatabase.BaseActivity

class ChangePasswordActivity :BaseActivity() {

    lateinit var binding:ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_change_password)
        initView()
    }

    private fun initView() {
        binding.btnUpdatePassword.setOnClickListener {
//            BasePreferencesManager.putBoolean(BasePreferencesManager.IS_SKIP,true)
            val intent = Intent(this@ChangePasswordActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}