package com.example.skylinepropertymanagement.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.app.Jump
import com.example.skylinepropertymanagement.app.onlyNew
import com.example.skylinepropertymanagement.app.setupToolbar
import com.example.skylinepropertymanagement.data.SessionManager
import com.example.skylinepropertymanagement.ui.fragment.FragmentViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.app_bar.view.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header.view.*
import java.util.jar.Manifest

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val viewModel: FragmentViewModel by viewModels()
    private val CAMERA_REQUEST_CODE = 100
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

        Jump.JUMP_TRIGGER.onlyNew(this).observe(this, Observer{
            checkCameraPermission()

        })

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_alert -> { navController.navigate(R.id.action_global_alertFragment)}
            R.id.nav_document -> {navController.navigate(R.id.action_global_documentsFragment)}
        }
        return true
    }

    override fun onBackPressed() {
        navController.navigate(R.id.action_global_homeFragment)
        if(drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START)
        } else {
            navController.navigate(R.id.action_global_homeFragment)
            super.onBackPressed()
        }
    }

    fun openCamera() {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,CAMERA_REQUEST_CODE )
    }

    fun checkCameraPermission() {
        var permission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)

        if(permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        } else {openCamera()}

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                var item = data!!.extras!!.get("data") as Bitmap
                viewModel.photoList.value?.add(item)
                viewModel.photoList.value = viewModel.photoList.value
            }
        }
    }
}