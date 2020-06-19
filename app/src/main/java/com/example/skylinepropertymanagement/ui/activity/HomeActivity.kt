package com.example.skylinepropertymanagement.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.app.setupToolbar
import com.example.skylinepropertymanagement.data.SessionManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.app_bar.view.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header.view.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer:DrawerLayout
    private lateinit var navView:NavigationView
    private lateinit var headerView:View
    val sm = SessionManager()

    val navController by lazy {findNavController(R.id.nav_fragment)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        init()
    }


    private fun init() {

        this.setupToolbar("Home")
        drawer = drawer_layout
        navView = nav_view
        headerView = navView.getHeaderView(0)
        navView.itemIconTintList = null


        headerView.text_view_email.text = sm.getEmail()
        headerView.text_view_type.text = sm.getType()


        var toggle = ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_alert -> { navController.navigate(R.id.action_global_alertFragment)}
            R.id.nav_document -> {navController.navigate(R.id.action_global_documentsFragment)}
        }
        return true
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {

            super.onBackPressed()
        }
    }
}