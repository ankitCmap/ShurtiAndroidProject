package com.example.shurtiandroidproject.ui

import android.content.Intent
import android.os.Bundle
import com.example.shurtiandroidproject.databinding.ActivityEditProfileBinding
import com.example.shurtiandroidproject.roomdatabase.BaseActivity

class EditProfileActivity:BaseActivity() {
        lateinit var binding:ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val i = Intent(this@EditProfileActivity, NewMainActivity::class.java)
        i.putExtra("position", "3")
        startActivity(i)
        finish()
    }
    private fun initView() {
        binding.apply {
            setSupportActionBar(toolbarProfile)
            supportActionBar!!.title = "Profile"
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }
}