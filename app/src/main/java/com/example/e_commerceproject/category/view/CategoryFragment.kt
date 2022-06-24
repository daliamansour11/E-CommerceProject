package com.example.e_commerceproject.category.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.e_commerceproject.R
import com.example.e_commerceproject.Settings.view.SettingsFragment
import com.example.e_commerceproject.category.model.CategoriesModel
import com.example.e_commerceproject.category.model.Product
import com.example.e_commerceproject.category.view.adapters.CategoryAdapter
import com.example.e_commerceproject.category.view.adapters.ViewPagerAdapter
import com.example.e_commerceproject.category.view.adapters.MyAdapter
import com.example.e_commerceproject.category.viewmodel.CategoryViewModel
import com.example.e_commerceproject.category.viewmodel.CategoryViewModelFactory
import com.example.e_commerceproject.details.view.DetailsFragment
import com.example.e_commerceproject.home.view.HomeFragment
import com.example.e_commerceproject.network.CategoryRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.google.android.material.tabs.TabLayout
import kotlin.streams.toList

class CategoryFragment : Fragment(), OnProductClickInterface  {

    lateinit var toolbar: Toolbar
    lateinit var category_back: ImageView
    lateinit var tabLayout: TabLayout
//    lateinit var viewPager: ViewPager
//    lateinit var searchClickListener: OnSearchClickListener
    var tabs: ArrayList<Fragment> = ArrayList()
    lateinit var searchView: SearchView


    private lateinit var categoryAdapter: CategoryAdapter
    lateinit var recyclerView: RecyclerView;
    lateinit var viewModel: CategoryViewModel

    lateinit var shosebtn: Button;
    lateinit var accessoriesbtn: Button;
    lateinit var t_shirtbtn: Button;
    var productList: List<Product> = ArrayList()

    var brandId = ""
    var collectionId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
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

        var args = this.arguments
        if(args == null){
            Toast.makeText(requireContext() , "null" , Toast.LENGTH_SHORT).show()
        }else{
            brandId =  args?.get("BRAND_ID") as String
            Log.i("TAG", "onViewCreatedmmmmmmmmmmmmmmmmmmmmmmm: ${brandId}")
         //   Toast.makeText(requireContext() , brandId , Toast.LENGTH_SHORT).show()
        }
        category_back = view.findViewById(R.id.categoryArrowBack)
        shosebtn = view.findViewById(R.id.Shose_button)
        accessoriesbtn = view.findViewById(R.id.Accessories_button)
        t_shirtbtn = view.findViewById(R.id.t_shirt_button)

        recyclerView = view.findViewById(R.id.CategoryRecycleview)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        categoryAdapter = CategoryAdapter(requireContext(), this)
        recyclerView.adapter = categoryAdapter


        val retrofitService = RetrofitService.getInstance()
        val mainRepository = CategoryRepository(retrofitService)

        viewModel = ViewModelProvider(this, CategoryViewModelFactory(mainRepository)).get(CategoryViewModel::class.java)
        viewModel.getCategoryProduct(collectionId, "", brandId)
        viewModel.categoryList.observe(viewLifecycleOwner, {

            productList = it.products
            categoryAdapter.setlist(it.products)
            categoryAdapter.notifyDataSetChanged()

        })

        Log.i("TAG", "onViewCreatedffffffffffffffffffffffffffffff: ${brandId}")
        shosebtn.setOnClickListener {
            viewModel.getCategoryProduct(collectionId, "SHOES", brandId)
            viewModel.subCategoryList.observe(viewLifecycleOwner, {
                productList = it.products
                categoryAdapter.setlist(it.products)
                categoryAdapter.notifyDataSetChanged()
            })
        }

        accessoriesbtn.setOnClickListener {
            viewModel.getCategoryProduct(collectionId, "ACCESSORIES", brandId)
            viewModel.subCategoryList.observe(viewLifecycleOwner, {
                productList = it.products
                categoryAdapter.setlist(it.products)
                categoryAdapter.notifyDataSetChanged()
            })
        }

        category_back.setOnClickListener {

            val homeFragment = HomeFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, homeFragment)?.commit()

        }

        t_shirtbtn.setOnClickListener {
            viewModel.getCategoryProduct(collectionId, "T-SHIRTS", brandId)
            viewModel.subCategoryList.observe(viewLifecycleOwner, {
                productList = it.products
                categoryAdapter.setlist(it.products)
                categoryAdapter.notifyDataSetChanged()
            })
        }


        ////////////////////////////////////////////////////////////////////////////////////////////

        tabLayout = view.findViewById(R.id.tabLayout)

        tabLayout.addTab(tabLayout.newTab().setText("All"))
        tabLayout.addTab(tabLayout.newTab().setText("Women"))
        tabLayout.addTab(tabLayout.newTab().setText("Kids"))
        tabLayout.addTab(tabLayout.newTab().setText("Men"))
        tabLayout.addTab(tabLayout.newTab().setText("Sales"))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                if(tab.position == 0){
                    collectionId = ""
                    viewModel.getCategoryProduct(collectionId, "", brandId)
                    viewModel.categoryList.observe(viewLifecycleOwner, {

                        productList = it.products
                        categoryAdapter.setlist(it.products)
                        categoryAdapter.notifyDataSetChanged()

                    })
                }else if(tab.position == 1){
                    collectionId = "273053712523"
                    viewModel.getCategoryProduct(collectionId, "", brandId)
                    viewModel.categoryList.observe(viewLifecycleOwner, {

                        productList = it.products
                        categoryAdapter.setlist(it.products)
                        categoryAdapter.notifyDataSetChanged()

                    })
                }else if(tab.position == 2) {
                    collectionId = "273053745291"
                    viewModel.getCategoryProduct(collectionId, "", brandId)
                    viewModel.categoryList.observe(viewLifecycleOwner, {

                        productList = it.products
                        categoryAdapter.setlist(it.products)
                        categoryAdapter.notifyDataSetChanged()

                    })
                } else if(tab.position == 3) {
                    collectionId = "273053679755"
                    viewModel.getCategoryProduct(collectionId, "", brandId)
                    viewModel.categoryList.observe(viewLifecycleOwner, {

                        productList = it.products
                        categoryAdapter.setlist(it.products)
                        categoryAdapter.notifyDataSetChanged()

                    })
                }else if(tab.position == 4) {
                        collectionId = "273053778059"
                        viewModel.getCategoryProduct(collectionId, "", brandId)
                        viewModel.categoryList.observe(viewLifecycleOwner, {

                            productList = it.products
                            categoryAdapter.setlist(it.products)
                            categoryAdapter.notifyDataSetChanged()

                        })

//                searchClickListener = tabs.get(tab.position) as OnSearchClickListener
            }

        }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {
             //   searchClickListener = tabs.get(tab.position) as OnSearchClickListener
            }

        })

        searchView = view.findViewById(R.id.categorySearch)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
                    categoryAdapter.setlist(productList)
                    categoryAdapter.notifyDataSetChanged()
                    return true
                }
                return false
            }
        })

    }

    override fun onProductClick(id :Long) {
        Toast.makeText(requireContext(), "iui", Toast.LENGTH_SHORT).show()
        var bundle = Bundle()
        bundle.putLong("productid" , id)
        val detailsfragment = DetailsFragment()
        detailsfragment.arguments = bundle
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, detailsfragment)?.commit()
    }


}