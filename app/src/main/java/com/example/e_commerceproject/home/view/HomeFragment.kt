package com.example.e_commerceproject.home.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.e_commerceproject.R
import com.example.e_commerceproject.category.view.CategoryFragment
import com.example.e_commerceproject.common.network.NetworkUtils
import com.example.e_commerceproject.currencyConverter.view.CURRUNEY_TYPE
import com.example.e_commerceproject.currencyConverter.view.SHARD_NAME
import com.example.e_commerceproject.home.client.HomeClient
import com.example.e_commerceproject.home.model.HomeRepository
import com.example.e_commerceproject.home.model.ViewPagerAdapter
import com.example.e_commerceproject.home.viewmodel.CouponsViewModel
import com.example.e_commerceproject.home.viewmodel.CouponsViewModelFactory
import com.example.e_commerceproject.home.viewmodel.HomeViewModel
import com.example.e_commerceproject.home.viewmodel.HomeViewModelFactory
import com.example.e_commerceproject.homesearch.view.HomeSearchFragment
import com.example.e_commerceproject.network.remotesource.CouponsRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.example.e_commerceproject.payment.view.CashFragment
import com.example.e_commerceproject.profile.view.ProfileFragment


class HomeFragment : Fragment() , OnBrandClickListener{
    lateinit var coupon_viewModel: CouponsViewModel
    lateinit var coupon__vmFactory: CouponsViewModelFactory
    lateinit var   coupon_recycler:RecyclerView
    lateinit var viewModel: HomeViewModel
    lateinit var vmFactory: HomeViewModelFactory
    lateinit var recyclerView: RecyclerView
    lateinit var brandsAdapter: BrandsAdapter
    lateinit var couponsAdapter: CouponsAdapter
    lateinit var homeFragmentView: View
    lateinit var brandLogo: ImageView
    lateinit var profileScreen: ImageView
    lateinit var searchScreenIcon: ImageView
    var viewPager: ViewPager? =null
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var imageList: List<Int>
    lateinit var sharedPref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
        val sharedPref =requireActivity() .getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE) ?: return
        val str_name = sharedPref.getString(CURRUNEY_TYPE, "")
        System.out.println("name = "+str_name)
        //Toast.makeText(this, "$str_name $int_number", Toast.LENGTH_LONG).show()
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
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = CouponsRepository(retrofitService)
        coupon_viewModel = ViewModelProvider(
            this,
            CouponsViewModelFactory(mainRepository)
        ).get(CouponsViewModel::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        // Inflate the layout for this fragment
//        val name= sharedPref.getString("CURRUNEY_TYPE","")
//        System.out.println("name="+name)
//        Log.i("\n\n viewModel","---------------------------"+name+"\n\n")
//

        homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false)

        viewPager = homeFragmentView.findViewById(R.id.idViewPager) as ViewPager
        return homeFragmentView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI(view)
        viewModel.brandList.observe(viewLifecycleOwner){brands ->
            brandsAdapter.setDataList(brands)
            brandsAdapter.notifyDataSetChanged()
        }

        initCoupon(view)
        coupon_viewModel.getCoupon()
        coupon_viewModel._coupons .observe(viewLifecycleOwner){coupons ->
            Log.i("TAG", "onViewCreated: ${coupons}")
            couponsAdapter.setDataList(coupons.discount_codes)
            couponsAdapter.notifyDataSetChanged()
        }
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.ads1
        imageList = imageList + R.drawable.ads2
        imageList = imageList + R.drawable.ads3
        imageList = imageList + R.drawable.ads4
        imageList = imageList + R.drawable.ads5
        imageList = imageList + R.drawable.ads6
        imageList = imageList + R.drawable.ads7
        imageList = imageList + R.drawable.ads8
        imageList = imageList + R.drawable.ads9

        viewPagerAdapter = ViewPagerAdapter(requireContext(), imageList)
        viewPager!!.adapter = viewPagerAdapter

        // on below line we are initializing our view
        // pager adapter and adding image list to it.
        //  viewPagerAdapter = ViewPagerAdapter(this, imageList)

        // on below line we are setting
        // adapter to our view pager.
        //  viewPager.adapter = viewPagerAdapter

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

    override fun OnBrandClick(id:String) {
        var bundle = Bundle()
        bundle.putString("BRAND_ID" , id)
        val categoryFragment = CategoryFragment()
        categoryFragment.arguments = bundle
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, categoryFragment)?.commit()

    }

    override fun onDiscountCartClick(_code:String) {
        var bundle=Bundle()
        bundle.putString("COUPON",_code)

        val cashFragment = CashFragment()
        cashFragment.arguments =bundle
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, cashFragment)?.commit()

    }
    fun initCoupon(view: View){
        coupon_recycler= view.findViewById(R.id.coupons_recyclerview)
        val couponLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        couponLinearLayoutManager.orientation= RecyclerView.HORIZONTAL
        couponsAdapter= CouponsAdapter(requireContext(), this)
        coupon_recycler.layoutManager=couponLinearLayoutManager
        coupon_recycler.adapter=couponsAdapter


    }}