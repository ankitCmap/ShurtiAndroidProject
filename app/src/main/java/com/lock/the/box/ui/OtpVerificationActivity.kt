package com.lock.the.box.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lock.the.box.R
import com.lock.the.box.databinding.ActivityOtpVerificationBinding

import com.lock.the.box.roomdatabase.BaseActivity

class OtpVerificationActivity: BaseActivity() {
    lateinit var binding: ActivityOtpVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_otp_verification)

    }
}