package com.example.e_commerceproject.category.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.e_commerceproject.category.view.KidsFragment
import com.example.e_commerceproject.category.view.MenFragment
import com.example.e_commerceproject.category.view.WomenFragment

class ViewPagerAdapter
@Suppress("DEPRECATION")
internal class MyAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                WomenFragment()
            }
            1 -> {
                MenFragment()
            }
            2 -> {
                KidsFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}