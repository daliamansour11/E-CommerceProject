package com.example.e_commerceproject.category.view.adapters

import android.content.Context
import android.service.quicksettings.Tile
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
    var totalTabs: Int,
    var tabs: ArrayList<Fragment>
) :
    FragmentPagerAdapter(fm)

{
    override fun getItem(position: Int): Fragment {
        return tabs.get(position)

//        return when (position) {
//            0 -> {
//                WomenFragment()
//            }
//            1 -> {
//                MenFragment()
//            }
//            2 -> {
//                KidsFragment()
//            }
//            else -> getItem(position)
//        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}


/*
class ViewPagerAdapter (supportFragmentManager: FragmentManager)
    : FragmentPagerAdapter (supportFragmentManager , BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }
    override fun getCount(): Int {
        return mFragmentList.size

    }

    fun addFragment(fragment: Fragment , title : String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}
*/