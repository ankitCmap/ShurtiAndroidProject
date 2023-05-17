package com.example.shurtiandroidproject.ui

import android.os.Bundle
import com.example.shurtiandroidproject.databinding.ActivityCartBinding
import com.example.shurtiandroidproject.roomdatabase.BaseActivity

class CartActivity:BaseActivity() {
    lateinit var binding:ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}