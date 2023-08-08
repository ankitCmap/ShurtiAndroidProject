package com.lock.the.box.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lock.the.box.R
import com.lock.the.box.databinding.ActivityChangePasswordBinding

import com.lock.the.box.adapter.helper.BasePreferencesManager
import com.lock.the.box.roomdatabase.BaseActivity

class ChangePasswordActivity : BaseActivity() {

    lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_change_password)
        initView()
    }

    private fun initView() {
        binding.btnUpdatePassword.setOnClickListener {
            BasePreferencesManager.putBoolean(BasePreferencesManager.IS_SKIP,true)
            val intent = Intent(this@ChangePasswordActivity, NewMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}