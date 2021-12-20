package com.d4u.shopping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import dagger.android.support.DaggerAppCompatActivity

class SplashActivity : DaggerAppCompatActivity() {
    private val SPLASH_TIME_OUT = 2000L
    private var delayHandler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (delayHandler == null) {
            delayHandler = Handler(Looper.getMainLooper()!!)
        }

        delayHandler!!.postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()

        }, SPLASH_TIME_OUT)

    }
    
    override fun onDestroy() {
        super.onDestroy()
        delayHandler!!.removeCallbacksAndMessages(null)
    }

}