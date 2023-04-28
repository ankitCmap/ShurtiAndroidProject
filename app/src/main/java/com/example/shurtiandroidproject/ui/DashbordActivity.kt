package com.example.shurtiandroidproject.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shurtiandroidproject.R
import com.example.shurtiandroidproject.databinding.ActivityDashbordBinding
import com.example.shurtiandroidproject.roomdatabase.BaseActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class DashbordActivity:BaseActivity() {
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding:ActivityDashbordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_dashbord)
        setSupportActionBar(binding.appBarMain.findViewById(R.id.toolbar))

        binding.appBarMain.findViewById<View>(R.id.fab).setOnClickListener { view ->
                Snackbar.make(view,"for demo replace with own features",Snackbar.LENGTH_SHORT)
                    .setAction("Action",null).show()
        }

        val drawerLayout:DrawerLayout=binding.drawerLayout
        val navView:NavigationView=binding.navView
        val navController=findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration= AppBarConfiguration(setOf(),drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController= findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}