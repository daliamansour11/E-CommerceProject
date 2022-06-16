package com.example.e_commerceproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.TranslateAnimation
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {

  lateinit var  splash_img : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler(Looper.myLooper()!!).postDelayed({

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

        }, 2000)


}}