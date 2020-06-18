package com.example.skylinepropertymanagement.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.app.setupToolbar
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.nav_header.view.*

class HomeActivity : AppCompatActivity() {
    private lateinit var drawer:DrawerLayout
    private lateinit var navView:NavigationView
    private lateinit var headerView:View

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

        headerView.text_view_email.text = "Test@Monado.com"
        headerView.text_view_type.text = "Test"

        var toggle = ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

    }
}