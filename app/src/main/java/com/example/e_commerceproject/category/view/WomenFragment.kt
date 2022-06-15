package com.example.e_commerceproject.category.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.category.model.CategoriesModel
import com.example.e_commerceproject.category.model.CategoryModel
import com.example.e_commerceproject.category.model.Product
import com.example.e_commerceproject.category.view.adapters.CategoryAdapter
import com.example.e_commerceproject.category.viewmodel.CategoryViewModel
import com.example.e_commerceproject.category.viewmodel.CategoryViewModelFactory
import com.example.e_commerceproject.network.CategoryRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.example.e_commerceproject.details.view.DetailsFragment
import com.example.e_commerceproject.home.client.HomeClient
import com.example.e_commerceproject.home.model.HomeRepository
import com.example.e_commerceproject.home.viewmodel.HomeViewModelFactory
import kotlin.streams.toList

class WomenFragment : Fragment(), OnProductClickInterface, OnSearchClickListener {

    private lateinit var categoryAdapter: CategoryAdapter
    lateinit var recyclerView: RecyclerView;
    lateinit var viewModel: CategoryViewModel

    lateinit var shosebtn: Button;
    lateinit var accessoriesbtn: Button;
    lateinit var t_shirtbtn: Button;
    var productList: List<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_women, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shosebtn = view.findViewById(R.id.women_Shose_button)
        accessoriesbtn = view.findViewById(R.id.women_Accessories_button)
        t_shirtbtn = view.findViewById(R.id.women_t_shirt_button)

        recyclerView = view.findViewById(R.id.womenCategoryRecycleview)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        categoryAdapter = CategoryAdapter(requireContext(), this)
        recyclerView.adapter = categoryAdapter


        val retrofitService = RetrofitService.getInstance()
        val mainRepository = CategoryRepository(retrofitService)

        viewModel = ViewModelProvider(
            this,
            CategoryViewModelFactory(mainRepository)
        ).get(CategoryViewModel::class.java)
        viewModel.getCategoryProduct("273053712523", "", "")
        viewModel.categoryList.observe(viewLifecycleOwner, {

            productList = it.products
            categoryAdapter.setlist(it.products)
            categoryAdapter.notifyDataSetChanged()

        })

        shosebtn.setOnClickListener {
            viewModel.getCategoryProduct("273053712523", "SHOES", "")
            viewModel.subCategoryList.observe(viewLifecycleOwner, {
                productList = it.products
                categoryAdapter.setlist(it.products)
                categoryAdapter.notifyDataSetChanged()
            })
        }

        accessoriesbtn.setOnClickListener {
            viewModel.getCategoryProduct("273053712523", "ACCESSORIES", "")
            viewModel.subCategoryList.observe(viewLifecycleOwner, {
                productList = it.products
                categoryAdapter.setlist(it.products)
                categoryAdapter.notifyDataSetChanged()
            })
        }

        t_shirtbtn.setOnClickListener {
            viewModel.getCategoryProduct("273053712523", "T-SHIRTS", "")
            viewModel.subCategoryList.observe(viewLifecycleOwner, {
                productList = it.products
                categoryAdapter.setlist(it.products)
                categoryAdapter.notifyDataSetChanged()
            })
        }
    }

    override fun onProductClick(id :Long) {
        Toast.makeText(requireContext(), "iui", Toast.LENGTH_SHORT).show()
        var bundle = Bundle()
        bundle.putLong("productid" , id)

        val detailsfragment = DetailsFragment()
        detailsfragment.arguments = bundle
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, detailsfragment)?.commit()


//        val intent = Intent(getActivity(), DetailsActivity::class.java)
//        intent.putExtra("id" , 1)
//        getActivity()?.startActivity(intent)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            var filteredProductList = productList.stream().filter { product ->

                product.title.lowercase().contains(query.lowercase())

            }.toList()
            categoryAdapter.setlist(filteredProductList)
            categoryAdapter.notifyDataSetChanged()
            return true
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null && newText.length==0) {
            categoryAdapter.setlist(emptyList<Product>())
            categoryAdapter.notifyDataSetChanged()
            return true
        }
        return false
    }

}