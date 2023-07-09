package com.lock.the.box.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment

import com.lock.the.box.roomdatabase.BaseActivity
import com.lock.the.box.ui.account.AccountFragment
import com.lock.the.box.ui.home.HomeFragment
import com.lock.the.box.viewmodel.MainVM
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.lock.the.box.R
import com.lock.the.box.databinding.ActivityDashMainBinding
import com.lock.the.box.ui.gallery.GalleryFragment
import kotlin.system.exitProcess


class NewMainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener {
    lateinit var binding: ActivityDashMainBinding
    lateinit var viewModel: MainVM
    var doubleBackToExitPressedOnce = false
    var mToolbar: Toolbar? = null
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mToolbar = findViewById(R.id.toolbar_a)
        setSupportActionBar(mToolbar)
        // drawer layout instance to toggle the menu icon to open

        // drawer and back button to close drawer

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this@NewMainActivity,
            binding.drawerLayout,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        val drawable =
            ResourcesCompat.getDrawable(resources, R.drawable.nav_icon, theme)
        actionBarDrawerToggle.isDrawerIndicatorEnabled = false
        actionBarDrawerToggle.setHomeAsUpIndicator(drawable)
        actionBarDrawerToggle.toolbarNavigationClickListener = View.OnClickListener {
            if (binding.drawerLayout.isDrawerVisible(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setCheckedItem(R.id.nav_account)
        replaceFragment(HomeFragment(), "Home")
        binding.navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.nav_account -> replaceFragment(HomeFragment(), it.title.toString())
                R.id.nav_settings -> replaceFragment(GalleryFragment(), it.title.toString())
                R.id.nav_logout -> replaceFragment(HomeFragment(), it.title.toString())
            }
            true
        }

        if (intent.getStringExtra("position") != null) {
            when (intent.getStringExtra("position")!!.toInt()) {
                2 -> replaceFragment(AccountFragment(), "Account")
            }
        } else {
            replaceFragment(HomeFragment(), "Home")
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

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.flFragment, fragment)
        fragmentTransition.commit()
        setTitle(title)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}