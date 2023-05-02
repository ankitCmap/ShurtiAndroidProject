package com.example.shurtiandroidproject.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.network.RetrofitHelper
import com.example.shurtiandroidproject.adapter.SampleAdapter
import com.example.shurtiandroidproject.databinding.ActivityMainBinding
import com.example.shurtiandroidproject.helper.Utils
import com.example.shurtiandroidproject.network.WebServices
import com.example.shurtiandroidproject.repository.MainRepository
import com.example.shurtiandroidproject.roomdatabase.BaseActivity
import com.example.shurtiandroidproject.ui.gallery.GalleryFragment
import com.example.shurtiandroidproject.ui.home.HomeFragment
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
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = getViewModel()
        // drawer layout instance to toggle the menu icon to open

        // drawer and back button to close drawer
        binding.apply {
            actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.nav_open, R.string.nav_close)
            drawerLayout.addDrawerListener(actionBarDrawerToggle)
            actionBarDrawerToggle.syncState()

            // to make the Navigation drawer icon always appear on the action bar
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navView.setCheckedItem(R.id.nav_account)
            replaceFragment(HomeFragment(),"Home")
            navView.setNavigationItemSelectedListener {
                it.isChecked=true
                when(it.itemId){
                    R.id.nav_account-> replaceFragment(HomeFragment(),it.title.toString())
                    R.id.nav_settings-> replaceFragment(GalleryFragment(),it.title.toString())
                    R.id.nav_logout-> replaceFragment(HomeFragment(),it.title.toString())
                }
                true
            }
        }

        initView()
        onObservable()
        binding.mainData=viewModel
    }
    private fun onObservable() {

       /* viewModel.dataList.observe(this) {
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
        }*/
    }

    private fun initView() {
        /*binding.ivSearch.setBackgroundResource(R.mipmap.search)
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
        }*/
    }

    @JvmName("getViewModel1")
    private fun getViewModel(): MainVM {
        repository = MainRepository(RetrofitHelper.createRetrofitService(WebServices::class.java))
        return ViewModelProvider(this, MainVM.Factory(repository))["", MainVM::class.java]
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        }
            return super.onOptionsItemSelected(item)

    }

    private fun replaceFragment(fragment : Fragment,title : String){
        val fragmentManager=supportFragmentManager
        val fragmentTransition=fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frame_layout,fragment)
        fragmentTransition.commit()
        binding.drawerLayout.closeDrawers()
        setTitle(title)
    }
}