package com.example.shurtiandroidproject.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.databinding.ActivityLogingPasswordBinding
import com.example.shurtiandroidproject.roomdatabase.BaseActivity

class LoginWithPasswordActivity:BaseActivity() {
    lateinit var binding: ActivityLogingPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_loging_password)
    }
}