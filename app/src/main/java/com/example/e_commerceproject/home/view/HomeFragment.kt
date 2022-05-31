package com.example.e_commerceproject.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.home.model.DummyData


class HomeFragment : Fragment() {


    lateinit var recyclerView: RecyclerView
    lateinit var brandsAdapter: BrandsAdapter
    lateinit var homeFragmentView: View
    lateinit var brandLogo: ImageView
    lateinit var profileScreen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        // Inflate the layout for this fragment
        homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false)
        return homeFragmentView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=view.findViewById(R.id.brandsRecycleView)
        val brandsLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        brandsLinearLayoutManager.orientation= RecyclerView.HORIZONTAL
        brandsAdapter= BrandsAdapter(requireContext())
        brandsAdapter.setDataList(DummyData.BRAND_DATA)
        recyclerView.layoutManager=brandsLinearLayoutManager
        recyclerView.adapter=brandsAdapter

        profileScreen=homeFragmentView.findViewById(R.id.profileIconScreen)

        profileScreen.setOnClickListener(View.OnClickListener {
            val fragmentManager=parentFragmentManager
            val fragmentTransaction=fragmentManager.beginTransaction()

//            fragmentTransaction.replace(R.id.,fragment)
            fragmentTransaction.commit()
        })

    }


}