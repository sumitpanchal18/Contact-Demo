package com.sumit.daggerHiltStructure.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.sumit.daggerHiltStructure.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

//        window.decorView.systemUiVisibility =
//            android.view.View.SYSTEM_UI_FLAG_FULLSCREEN

        Handler().postDelayed(
            {
                startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                finish()
            }, 2000
        )
    }
}