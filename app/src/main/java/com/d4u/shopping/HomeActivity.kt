package com.d4u.shopping

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity

class HomeActivity : DaggerAppCompatActivity() {

    private val END_SCALE = 0.85f
    private var mAppBarConfiguration: AppBarConfiguration? = null
    private var navController: NavController? = null
    private var drawer: DrawerLayout? = null
    private var navigationView: NavigationView? = null
    private var bottomNavView: BottomNavigationView? = null
    private var contentView: CoordinatorLayout? = null
    private var searchMenuVisible = true
    var menu: Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initFab()
        initNavigation()
        //showBottomNavigation(false);
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initFab() {
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun initNavigation() {
        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        bottomNavView = findViewById(R.id.bottom_nav_view)
        contentView = findViewById(R.id.content_view)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = AppBarConfiguration.Builder(
                R.id.nav_aboutus,
                R.id.nav_feedback,
                R.id.bottom_home, R.id.bottom_product, R.id.nav_notification)
                .setOpenableLayout(drawer) //                .setDrawerLayout(drawer)
                .build()
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController!!, mAppBarConfiguration!!)
        NavigationUI.setupWithNavController(navigationView!!, navController!!)
        NavigationUI.setupWithNavController(bottomNavView!!, navController!!)

//        bottomNavView.setVisibility(View.GONE);
        navController!!.addOnDestinationChangedListener { controller, destination, arguments ->
            if (menu != null) {


                when (destination.label.toString()) {
                    "D4U" -> {
                        searchMenuVisible = true
                        bottomNavView!!.setVisibility(View.VISIBLE)

                    }
                    "About us" -> {
                        menu?.findItem(R.id.nav_search)?.setVisible(false)
                        searchMenuVisible = false
                        bottomNavView!!.setVisibility(View.GONE)
                    }
                    "Notifications" -> bottomNavView!!.setVisibility(View.GONE)
                    "Terms and Condition" -> bottomNavView!!.setVisibility(View.GONE)
                    "Feedback" -> bottomNavView!!.setVisibility(View.GONE)
                    "Settings" -> bottomNavView!!.setVisibility(View.GONE)
                    "Search" -> bottomNavView!!.setVisibility(View.GONE)
                    "Book Marks" -> bottomNavView!!.setVisibility(View.GONE)
                    "OfferDetails" -> bottomNavView!!.setVisibility(View.GONE)
                }
            }
            animateNavigationDrawer()

        }
    }


    private fun animateNavigationDrawer() {
//        drawerLayout.setScrimColor(getResources().getColor(R.color.text_brown));
        drawer!!.addDrawerListener(object : SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

                // Scale the View based on current slide offset
                val diffScaledOffset = slideOffset * (1 - END_SCALE)
                val offsetScale = 1 - diffScaledOffset
                contentView!!.scaleX = offsetScale
                contentView!!.scaleY = offsetScale

                // Translate the View, accounting for the scaled width
                val xOffset = drawerView.width * slideOffset
                val xOffsetDiff = contentView!!.width * diffScaledOffset / 2
                val xTranslation = xOffset - xOffsetDiff
                contentView!!.translationX = xTranslation
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        this.menu = menu
        val shareItem = menu.findItem(R.id.nav_search)
        if (shareItem != null) {
//            shareItem.setVisible(true)
        }
        /*    if (searchMenuVisible) {
            shareItem.setVisible(false);
        }else{
            shareItem.setVisible(true);
        }*/return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(navController, mAppBarConfiguration!!)
                || super.onSupportNavigateUp())
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}