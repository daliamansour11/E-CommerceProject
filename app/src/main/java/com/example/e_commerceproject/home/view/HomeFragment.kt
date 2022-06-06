package com.example.e_commerceproject.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.brands.view.BrandsFragment
import com.example.e_commerceproject.category.view.CategoryFragment
import com.example.e_commerceproject.common.network.NetworkUtils
import com.example.e_commerceproject.home.client.HomeClient
import com.example.e_commerceproject.home.model.HomeRepository
import com.example.e_commerceproject.home.viewmodel.HomeViewModel
import com.example.e_commerceproject.home.viewmodel.HomeViewModelFactory
import com.example.e_commerceproject.profile.view.ProfileFragment


class HomeFragment : Fragment() , OnBrandClickListener{

    lateinit var viewModel: HomeViewModel
    lateinit var vmFactory: HomeViewModelFactory
    lateinit var recyclerView: RecyclerView
    lateinit var brandsAdapter: BrandsAdapter
    lateinit var homeFragmentView: View
    lateinit var brandLogo: ImageView
    lateinit var profileScreen: ImageView
    lateinit var searchScreenIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Getting ViewModel Ready
        vmFactory = HomeViewModelFactory(
            HomeRepository.getInstance(
                HomeClient.getInstance(),
                requireContext()
        ))
        viewModel = ViewModelProvider(this, vmFactory).get(HomeViewModel::class.java)

        if(NetworkUtils.isOnline(requireContext())){
            viewModel.getAllBrands()
        } else {
            Toast.makeText(requireContext(), "Please Check your network connection", Toast.LENGTH_LONG).show()
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        // Inflate the layout for this fragment
        homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false)
        return homeFragmentView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
        viewModel.brandList.observe(viewLifecycleOwner){brands ->
            brandsAdapter.setDataList(brands)
            brandsAdapter.notifyDataSetChanged()
        }


    }

    private fun initUI(view: View){
        recyclerView=view.findViewById(R.id.brandsRecycleView)
        val brandsLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        brandsLinearLayoutManager.orientation= RecyclerView.HORIZONTAL
        brandsAdapter= BrandsAdapter(requireContext(), this)
        recyclerView.layoutManager=brandsLinearLayoutManager
        recyclerView.adapter=brandsAdapter
        profileScreen=view.findViewById(R.id.profileIconScreen)
        searchScreenIcon=view.findViewById(R.id.searchIconScreen)

        profileScreen.setOnClickListener(View.OnClickListener {
            val fragmentManager=parentFragmentManager
            val fragmentTransaction=fragmentManager.beginTransaction()

            val profileFragment = ProfileFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, profileFragment)?.commit()

        })

        searchScreenIcon.setOnClickListener(View.OnClickListener {
            val fragmentManager=parentFragmentManager
            val fragmentTransaction=fragmentManager.beginTransaction()

            val searchFragment = HomeSearchFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, searchFragment)?.commit()
        })
    }

    override fun OnBrandClick() {
        val categoryFragment = CategoryFragment()
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, categoryFragment)?.commit()

    }


}