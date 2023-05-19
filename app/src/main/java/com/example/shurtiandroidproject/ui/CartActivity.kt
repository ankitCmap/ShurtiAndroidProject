package com.example.shurtiandroidproject.ui

import android.content.Intent
import android.os.Bundle
import com.example.shurtiandroidproject.databinding.ActivityCartBinding
import com.example.shurtiandroidproject.helper.CartBottomSheetDialog
import com.example.shurtiandroidproject.helper.ShurtiApplication
import com.example.shurtiandroidproject.roomdatabase.BaseActivity


class CartActivity:BaseActivity() {

    lateinit var binding:ActivityCartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvSmallCount.text= ShurtiApplication.instance!!.s.toString()
        binding.tvMediumCount.text= ShurtiApplication.instance!!.m.toString()
        binding.tvLargeCount.text= ShurtiApplication.instance!!.l.toString()

        initView()

    }

    override fun onResume() {
        super.onResume()
        binding.tvSmallCount.text= ShurtiApplication.instance!!.s.toString()
        binding.tvMediumCount.text= ShurtiApplication.instance!!.m.toString()
        binding.tvLargeCount.text= ShurtiApplication.instance!!.l.toString()
    }

    override fun onStart() {
        super.onStart()
        binding.tvSmallCount.text= ShurtiApplication.instance!!.s.toString()
        binding.tvMediumCount.text= ShurtiApplication.instance!!.m.toString()
        binding.tvLargeCount.text= ShurtiApplication.instance!!.l.toString()
    }

    override fun onPause() {
        super.onPause()
        binding.tvSmallCount.text= ShurtiApplication.instance!!.s.toString()
        binding.tvMediumCount.text= ShurtiApplication.instance!!.m.toString()
        binding.tvLargeCount.text= ShurtiApplication.instance!!.l.toString()
    }


    private fun initView() {
        binding.apply {

            ivBack.setOnClickListener {
                val i = Intent(this@CartActivity, NewMainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
                finish()
            }

            ivAdd.setOnClickListener {
                val modal = CartBottomSheetDialog()
                supportFragmentManager.let { modal.show(it, CartBottomSheetDialog.TAG) }
            }
        }

    }




}