package com.example.e_commerceproject.details.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.e_commerceproject.R
import com.example.e_commerceproject.category.view.WomenFragment

class DetailsActivity : AppCompatActivity() {

    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            val fragment = WomenFragment()
            showFragment(fragment)
        }
    }
    fun showFragment(fragment: WomenFragment){
        val fram = supportFragmentManager.beginTransaction()
        //fram.replace(R.id.fragment_main,fragment)
        fram.commit()
    }
}