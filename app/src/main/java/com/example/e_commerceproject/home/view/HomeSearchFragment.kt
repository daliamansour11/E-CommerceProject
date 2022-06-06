package com.example.e_commerceproject.home.view

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
import com.example.e_commerceproject.home.viewmodel.HomeSearchViewModel
import com.example.e_commerceproject.R
import com.example.e_commerceproject.common.network.NetworkUtils
import com.example.e_commerceproject.home.client.HomeClient
import com.example.e_commerceproject.home.model.HomeRepository
import com.example.e_commerceproject.home.model.ProductModel
import com.example.e_commerceproject.home.viewmodel.HomeSearchViewModelFactory
import com.example.e_commerceproject.home.viewmodel.HomeViewModelFactory
import java.util.function.Predicate
import kotlin.streams.toList

class HomeSearchFragment : Fragment(){

    lateinit var viewModel: HomeSearchViewModel
    lateinit var vmFactory: HomeSearchViewModelFactory
    lateinit var recyclerView: RecyclerView
    lateinit var homeSearchAdapter: HomeSearchAdapter
    lateinit var homeSearchFragmentView: View
    lateinit var productImage: ImageView
    lateinit var searchView: SearchView
    private var productList:List<ProductModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Getting ViewModel Ready
        vmFactory = HomeSearchViewModelFactory(
            HomeRepository.getInstance(
                HomeClient.getInstance(),
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
        return homeSearchFragmentView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
        viewModel.productList.observe(viewLifecycleOwner){products ->
            productList = products
            homeSearchAdapter.setDataList(products)
            homeSearchAdapter.notifyDataSetChanged()
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

                return false
            }
        })
    }




}