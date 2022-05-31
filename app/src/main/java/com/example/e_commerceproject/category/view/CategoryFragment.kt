package com.example.e_commerceproject.category.view

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.example.e_commerceproject.R
import com.example.e_commerceproject.category.model.CategoriesModel
import com.example.e_commerceproject.category.view.adapters.MyAdapter
import com.google.android.material.tabs.TabLayout

class CategoryFragment : Fragment()  {

    lateinit var toolbar: Toolbar
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  toolbar =

        val title = "KotlinApp"

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Football"))
        tabLayout.addTab(tabLayout.newTab().setText("Cricket"))
        tabLayout.addTab(tabLayout.newTab().setText("NBA"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = getFragmentManager()?.let {
            MyAdapter(requireContext(), it,
                tabLayout.tabCount)
        }
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater = inflater
        inflater.inflate(R.menu.categorytoolbarmenue , menu)



        val search = menu.findItem(R.id.searh)
        val searchView = search.actionView as SearchView
        val editText = searchView.findViewById<EditText>(R.id.searh)
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })
        return super.onCreateOptionsMenu(menu , inflater)

    }




}