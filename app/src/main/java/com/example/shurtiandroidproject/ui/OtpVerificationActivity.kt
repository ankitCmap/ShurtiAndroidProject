package com.example.shurtiandroidproject.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.databinding.ActivityOtpVerificationBinding
import com.example.shurtiandroidproject.roomdatabase.BaseActivity

class OtpVerificationActivity:BaseActivity() {
    lateinit var binding:ActivityOtpVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_otp_verification)

    }
}