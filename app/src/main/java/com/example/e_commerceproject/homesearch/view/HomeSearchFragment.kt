package com.example.e_commerceproject.homesearch.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.homesearch.viewmodel.HomeSearchViewModel
import com.example.e_commerceproject.R
import com.example.e_commerceproject.common.network.NetworkUtils
import com.example.e_commerceproject.homesearch.client.HomeSearchClient
import com.example.e_commerceproject.homesearch.model.HomeSearchRepository
import com.example.e_commerceproject.homesearch.model.ProductModel
import com.example.e_commerceproject.home.view.HomeFragment
import com.example.e_commerceproject.homesearch.viewmodel.HomeSearchViewModelFactory
import kotlin.streams.toList

class HomeSearchFragment : Fragment(){

    lateinit var viewModel: HomeSearchViewModel
    lateinit var vmFactory: HomeSearchViewModelFactory
    lateinit var recyclerView: RecyclerView
    lateinit var homeSearchAdapter: HomeSearchAdapter
    lateinit var homeSearchFragmentView: View
    lateinit var productImage: ImageView
    lateinit var searchView: SearchView
    lateinit var homeSearchArrowBack:ImageView
    private var productList:List<ProductModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Getting ViewModel Ready
        vmFactory = HomeSearchViewModelFactory(
            HomeSearchRepository.getInstance(
                HomeSearchClient.getInstance(),
                requireContext()
            ))
        viewModel = ViewModelProvider(this, vmFactory).get(HomeSearchViewModel::class.java)

        if(NetworkUtils.isOnline(requireContext())){
            viewModel.getAllProducts()
        } else {
            Toast.makeText(requireContext(), "Please Check your network connection", Toast.LENGTH_LONG).show()
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        // Inflate the layout for this fragment
        homeSearchFragmentView = inflater.inflate(R.layout.fragment_home_search, container, false)
        homeSearchArrowBack = homeSearchFragmentView.findViewById(R.id.homeSearchArrowBack)
        return homeSearchFragmentView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
        viewModel.productList.observe(viewLifecycleOwner){products ->
            productList = products
//            homeSearchAdapter.setDataList(products)
//            homeSearchAdapter.notifyDataSetChanged()
        }


    }

    private fun initUI(view: View){
        recyclerView=view.findViewById(R.id.homeSearchRecyclerView)
        val homeSearchLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        homeSearchLinearLayoutManager.orientation= RecyclerView.VERTICAL
        recyclerView.layoutManager=homeSearchLinearLayoutManager
        homeSearchAdapter = HomeSearchAdapter(requireContext())
        recyclerView.adapter=homeSearchAdapter

        searchView= view.findViewById(R.id.homeSearchView)

        homeSearchArrowBack.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                var bundle = Bundle()
                val df = HomeFragment()
                df.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, df)
                    ?.commit()
            }


        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    var filteredProductList = productList.stream().filter { product ->

                        product.title.lowercase().contains(query.lowercase())

                    }.toList()
                    homeSearchAdapter.setDataList(filteredProductList)
                    homeSearchAdapter.notifyDataSetChanged()
                    searchView.clearFocus()
                    return true
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.length==0) {
                    homeSearchAdapter.setDataList(emptyList<ProductModel>())
                    homeSearchAdapter.notifyDataSetChanged()
                    searchView.clearFocus()
                    return true
                }
                return false
            }
        })
    }




}