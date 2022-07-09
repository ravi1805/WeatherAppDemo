package com.ardentsoft.weather.demo.presentation

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import com.ardentsoft.weather.demo.R
import com.ardentsoft.weather.demo.presentation.utils.Navigation
import com.ardentsoft.weather.demo.presentation.view.CityListActivity
import dagger.android.AndroidInjection

private const val SPLASH_SCREEN_TIMEOUT = 3000L

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        setContentView(R.layout.activity_splash)
        navigateToCityListActivity()
    }


    private fun navigateToCityListActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            Navigation.navigateScreen(this, CityListActivity::class.java, true)
            finish()
        }, SPLASH_SCREEN_TIMEOUT)
    }
}
