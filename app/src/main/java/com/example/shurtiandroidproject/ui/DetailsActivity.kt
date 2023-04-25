package com.example.shurtiandroidproject.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.databinding.ActivityDetailsBinding
import com.example.shurtiandroidproject.model.Meals
import com.example.shurtiandroidproject.network.RetrofitHelper
import com.example.shurtiandroidproject.network.WebServices
import com.example.shurtiandroidproject.repository.MainRepository
import com.example.shurtiandroidproject.roomdatabase.BaseActivity
import com.example.shurtiandroidproject.viewmodel.MainVM

@Suppress("DEPRECATION")
class DetailsActivity : BaseActivity() {
    private lateinit var viewModel: MainVM
    private lateinit var repository: MainRepository
    lateinit var binding:ActivityDetailsBinding
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
        return ViewModelProvider(this, MainVM.Factory(repository)).get("", MainVM::class.java)
    }
}