package com.lock.the.box.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.lock.the.box.roomdatabase.BaseActivity
import com.lock.the.box.ui.account.AccountFragment
import com.lock.the.box.ui.home.HomeFragment
import com.lock.the.box.viewmodel.MainVM
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lock.the.box.R
import com.lock.the.box.databinding.ActivityDashMainBinding
import kotlin.system.exitProcess


class NewMainActivity : BaseActivity() {
    lateinit var binding: ActivityDashMainBinding
    lateinit var viewModel: MainVM
    var doubleBackToExitPressedOnce = false

    override
     fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDashMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            if (intent.getStringExtra("position") != null) {
                when (intent.getStringExtra("position")!!.toInt()) {
                    2 -> replaceFragment(AccountFragment(),"Account")
                }
            } else {
                replaceFragment(HomeFragment(),"Home")
            }


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment(),"Home")
                    true
                }
               /* R.id.navigation_category -> {
                    replaceFragment(HomeFragment(),"Home")
                    true
                }*/
                R.id.navigation_cart -> {
                    val i = Intent(this@NewMainActivity, CartActivity::class.java)
                    startActivity(i)
                    true
                }
            /*    R.id.navigation_store -> {
                    replaceFragment(HomeFragment(),"Home")
                    true
                }*/
                R.id.navigation_accounts -> {
                    replaceFragment(AccountFragment(),"Account")
                    true
                }
                else -> false
            }
        }
    }



    private fun doublebackpress() {
        if (doubleBackToExitPressedOnce) {
            exitProcess(0)
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun replaceFragment(fragment : Fragment, title : String){
        val fragmentManager=supportFragmentManager
        val fragmentTransition=fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.flFragment,fragment)
        fragmentTransition.commit()
        setTitle(title)
    }
}