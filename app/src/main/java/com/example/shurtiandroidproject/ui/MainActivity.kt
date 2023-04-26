package com.example.shurtiandroidproject.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.network.RetrofitHelper
import com.example.shurtiandroidproject.adapter.SampleAdapter
import com.example.shurtiandroidproject.databinding.ActivityMainBinding
import com.example.shurtiandroidproject.helper.Utils
import com.example.shurtiandroidproject.network.WebServices
import com.example.shurtiandroidproject.repository.MainRepository
import com.example.shurtiandroidproject.roomdatabase.BaseActivity
import com.example.shurtiandroidproject.viewmodel.MainVM
import java.util.*

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainVM
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var repository: MainRepository
    private lateinit var sampleAdapter:SampleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = getViewModel()
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.myDrawerLayout, R.string.nav_open, R.string.nav_close)
        binding.myDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()
        onObservable()
        binding.mainData=viewModel
    }
    private fun onObservable() {

        viewModel.dataList.observe(this) {
            if (it==null){
                binding.empty.wallEmptyLl.visibility=View.VISIBLE
                sampleAdapter.cleareData()
            }else{
                binding.empty.wallEmptyLl.visibility=View.GONE
                sampleAdapter.setData(it)
            }


        }
        viewModel.showLoader.observe(this) {
            if (it){
                createProgressDialog(this)
            }else{
                dismissDialog()
            }
        }
    }

    private fun initView() {
        binding.ivSearch.setBackgroundResource(R.mipmap.search)
        viewModel.getOnlineData(this)
        sampleAdapter= SampleAdapter()
        binding.rvMain.adapter=sampleAdapter
        binding.llSearch.setOnClickListener {
            Utils.hideSoftKeyboard(this)
            if (viewModel.search) {
                binding.ivSearch.setBackgroundResource(R.drawable.ic_baseline_cancel_24)
                viewModel.search=false
                viewModel.searchData(binding.edtSearch.text.toString(), this)
            }else{
                binding.edtSearch.setText("")
                viewModel.search=true
                viewModel.getOnlineData(this)
                binding.ivSearch.setBackgroundResource(R.mipmap.search)
            }
        }
    }

    @JvmName("getViewModel1")
    private fun getViewModel(): MainVM {
        repository = MainRepository(RetrofitHelper.createRetrofitService(WebServices::class.java))
        return ViewModelProvider(this, MainVM.Factory(repository)).get("", MainVM::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}