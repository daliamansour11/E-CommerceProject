package com.example.e_commerceproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity2 : AppCompatActivity() {
    lateinit var splash_img: ImageView
    val APLASH_SCREEN =5000
    private lateinit var topAnimation : Animation
    private lateinit var bottomAnimation : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)


        //topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        //bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)
        splash_img = findViewById(R.id.splash)

        //splash_img.animation = bottomAnimation

        Handler().postDelayed({

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 3000)


//        makeSplashFullScreen()
//        startAnimation()
        // splashTimer()
    }

//    private fun makeSplashFullScreen() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            window.statusBarColor = Color.WHITE
//        }
//    }
//
//    private fun startAnimation() { // alpha animation
//        val alphaAnimation = AlphaAnimation(0.0f, 0.0f)
//        alphaAnimation.duration = 2000
//        splash_img = findViewById(R.id.splash)
//        splash_img.startAnimation(alphaAnimation)
//
//    }
//
//    private fun splashTimer() {
//
//    }
}