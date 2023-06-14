package com.lock.the.box.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lock.the.box.R
import com.lock.the.box.databinding.ActivityDetailsBinding

import com.lock.the.box.model.Meals
import com.lock.the.box.network.RetrofitHelper
import com.lock.the.box.network.WebServices
import com.lock.the.box.repository.MainRepository
import com.lock.the.box.roomdatabase.BaseActivity
import com.lock.the.box.viewmodel.MainVM

class DetailsActivity : BaseActivity() {
    private lateinit var viewModel: MainVM
    private lateinit var repository: MainRepository
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_details)
        viewModel = getViewModel()
        val bundle = intent.extras
        if (bundle != null) {
            viewModel.item = (intent.getSerializableExtra("Data") as? Meals)!!
        }
        binding.mainData=viewModel
        initview()
    }

    private fun initview() {
        binding.hederbar.cvBack.setOnClickListener { finish() }
    }

    @JvmName("getViewModel1")
    private fun getViewModel(): MainVM {
        repository = MainRepository(RetrofitHelper.createRetrofitService(WebServices::class.java))
        return ViewModelProvider(this, MainVM.Factory(repository))["", MainVM::class.java]
    }
}