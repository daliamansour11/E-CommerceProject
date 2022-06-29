package com.example.e_commerceproject.category.view

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.authentication.login.view.LoginFragment
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.model.LineItem
import com.example.e_commerceproject.cart.model.NoteAttribute
import com.example.e_commerceproject.category.model.Product
import com.example.e_commerceproject.category.view.adapters.CategoryAdapter
import com.example.e_commerceproject.category.viewmodel.CategoryViewModel
import com.example.e_commerceproject.category.viewmodel.CategoryViewModelFactory
import com.example.e_commerceproject.currencyConverter.viewModel.ConverterViewModel
import com.example.e_commerceproject.currencyConverter.viewModel.ConverterViewModelFactory
import com.example.e_commerceproject.details.view.DetailsFragment
import com.example.e_commerceproject.home.view.HomeFragment
import com.example.e_commerceproject.network.CategoryRepository
import com.example.e_commerceproject.network.ConverterRepository
import com.example.e_commerceproject.network.remotesource.ConverterApiService
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.example.e_commerceproject.profile.view.ProfileFragment
import com.google.android.material.tabs.TabLayout
import kotlin.streams.toList

class CategoryFragment : Fragment(), OnProductClickInterface , OnFavoriteButtonClickListener  {

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
    lateinit var profile : ImageView
    var productList: List<Product> = ArrayList()
    var brandId = ""
    var collectionId = ""
    var searchQueryText=""
    lateinit var CviewModel: ConverterViewModel

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

        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs", Context.MODE_PRIVATE)
        var userEmail: String = sharedPreferences.getString("EMAIL_LOGIN", "").toString()
        var currencyTtpe: String = sharedPreferences.getString("CURRENCY_TYPE_RESULT", "").toString()
        var converterResponse: String = sharedPreferences.getString("CURRENCY_CONVERTER_RESULT", "").toString()


        var args = this.arguments
        if(args == null){
        }else{
            brandId =  args?.get("BRAND_ID") as String
            Log.i("TAG", "onViewCreatedmmmmmmmmmmmmmmmmmmmmmmm: ${brandId}")
        }

        var from = "USD"
        var to = "EGP"
        if(currencyTtpe == "EGP"){
            var to = "EGP"
            from = "EGP"
        }else if (currencyTtpe == "USD"){
            var to = "EGP"
            from = "USD"
        }

        val retrofitServicee = ConverterApiService.getInstance()
        val mainRepositoryy = ConverterRepository(retrofitServicee)
        CviewModel = ViewModelProvider(this, ConverterViewModelFactory(mainRepositoryy)).get(ConverterViewModel::class.java)
/*
        CviewModel.getcontvertedResponse("6gojh955Of5UkFW6fPN3W2nq1Isj5BqC" ,to , "1" , from)
        CviewModel._Convert_Response.observe(viewLifecycleOwner) { respo ->

            if(respo!=null){
                Log.i(ContentValues.TAG, "onChangedDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD: ${respo.result}")
                System.out.println("Re" + respo.result)


                val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs" ,Context.MODE_PRIVATE)
                val editorr = sharedPreferences.edit()
                editorr.apply(){


                    putString("CURRENCY_CONVERTER_RESULT" ,  "${respo.result}")

                }.apply()
            }
        }

*/
        category_back = view.findViewById(R.id.categoryArrowBack)
        shosebtn = view.findViewById(R.id.Shose_button)
        accessoriesbtn = view.findViewById(R.id.Accessories_button)
        t_shirtbtn = view.findViewById(R.id.t_shirt_button)
        profile = view.findViewById(R.id.profileIconCategoryScreen)
        profile.setOnClickListener {
            val profileFragment = ProfileFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, profileFragment)?.commit()

        }

        recyclerView = view.findViewById(R.id.CategoryRecycleview)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        categoryAdapter = CategoryAdapter(requireContext(), this , this)
        recyclerView.adapter = categoryAdapter



        var currencyTtp: String = sharedPreferences.getString("CURRENCY_TYPE_RESULT", "").toString()
        var converterRespons: String = sharedPreferences.getString("CURRENCY_CONVERTER_RESULT", "").toString()

        categoryAdapter.setCurrency(currencyTtp , converterRespons )



        val retrofitService = RetrofitService.getInstance()
        val mainRepository = CategoryRepository(retrofitService)

        viewModel = ViewModelProvider(this, CategoryViewModelFactory(mainRepository)).get(CategoryViewModel::class.java)

        var flag = false


        viewModel.getFavoriteProducts()
        viewModel.favoriteProducts.observe(viewLifecycleOwner, {
            var arr = (it.draft_orders.filter { it.email == userEmail && it.note == "fav" })  // [0].line_items?.get(0)?.product_id.toString()
            Log.i("TAG", "OnRemoveFromFavoriteButtonClickListener: ${arr.count()}")

            var ar = mutableSetOf("")
            for ( i in 0..arr.size-1){
                ar.add(arr[i].line_items?.get(0)?.product_id.toString())
            }

            Log.i("TAG", "onViewCreatedssssssssssssssssssssssssssssssssssssssssssssssss: ${ar}")
            categoryAdapter.setDraftlist(ar)


                viewModel.getCategoryProduct(collectionId, "", brandId)
                viewModel.categoryList.observe(viewLifecycleOwner, {

                    productList = it.products
                    showProducts()

                })

                Log.i("TAG", "onViewCreatedffffffffffffffffffffffffffffff: ${brandId}")
                shosebtn.setOnClickListener {
                    viewModel.getCategoryProduct(collectionId, "SHOES", brandId)
                    viewModel.subCategoryList.observe(viewLifecycleOwner, {
                        productList = it.products
                        showProducts()
                    })
                }

                accessoriesbtn.setOnClickListener {
                    viewModel.getCategoryProduct(collectionId, "ACCESSORIES", brandId)
                    viewModel.subCategoryList.observe(viewLifecycleOwner, {
                        productList = it.products
                        showProducts()
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
                        showProducts()
                    })
                }






        })






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
                        showProducts()

                    })
                }else if(tab.position == 1){
                    collectionId = "274500059275"
                    viewModel.getCategoryProduct(collectionId, "", brandId)
                    viewModel.categoryList.observe(viewLifecycleOwner, {

                        productList = it.products
                        showProducts()

                    })
                }else if(tab.position == 2) {
                    collectionId = "274500092043"
                    viewModel.getCategoryProduct(collectionId, "", brandId)
                    viewModel.categoryList.observe(viewLifecycleOwner, {

                        productList = it.products
                        showProducts()

                    })
                } else if(tab.position == 3) {
                    collectionId = "274500026507"
                    viewModel.getCategoryProduct(collectionId, "", brandId)
                    viewModel.categoryList.observe(viewLifecycleOwner, {

                        productList = it.products
                        showProducts()

                    })
                }else if(tab.position == 4) {
                    collectionId = "274500124811"

                    viewModel.getCategoryProduct(collectionId, "", brandId)
                    viewModel.categoryList.observe(viewLifecycleOwner, {

                        productList = it.products
                        showProducts()

                    })

//                searchClickListener = tabs.get(tab.position) as OnSearchClickListener
                }

            }

            ////////////////////////////////////////////////////////////



            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {
                //   searchClickListener = tabs.get(tab.position) as OnSearchClickListener
            }

        })





        //////////////////////////////////////////////////////////////


        searchView = view.findViewById(R.id.categorySearch)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchQueryText = query
                    var filteredProductList = filterData(query)
                    categoryAdapter.setlist(filteredProductList)
                    categoryAdapter.notifyDataSetChanged()
                    searchView.clearFocus()
                    return true
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.length==0) {
                    searchQueryText = newText
                    categoryAdapter.setlist(productList)
                    categoryAdapter.notifyDataSetChanged()
                    searchView.clearFocus()
                    return true
                }
                return false
            }
        })

    }

    private fun filterData(query: String): List<Product> {
        var filteredProductList = productList.stream().filter { product ->

            product.title.lowercase().contains(query.lowercase())

        }.toList()
        return filteredProductList
    }

    private fun showProducts(){
        if (searchQueryText!=null && searchQueryText.length>0){
            var filteredProductList = filterData(searchQueryText)
            categoryAdapter.setlist(filteredProductList)
        }else {
            categoryAdapter.setlist(productList)
        }
        categoryAdapter.notifyDataSetChanged()
    }
    override fun onProductClick(id :Long) {
        var bundle = Bundle()
        bundle.putLong("productid" , id)
        val detailsfragment = DetailsFragment()
        detailsfragment.arguments = bundle
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, detailsfragment)?.commit()
    }

    override fun OnFavoriteButtonClickListener(data: Product) {

        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs", Context.MODE_PRIVATE)
        var userEmail: String = sharedPreferences.getString("EMAIL_LOGIN", "").toString()


        if (userEmail == null || userEmail == "") {
            // navigate to login screen
            Toast.makeText(requireContext(), "you must login or register first", Toast.LENGTH_SHORT).show()
            val loginFragment = LoginFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, loginFragment)?.commit()

        } else {

            var item_Image1 = listOf<NoteAttribute>(NoteAttribute("image", data.images[0].src))

            var lineItem1 = LineItem(variant_id = data.variants[0].id, quantity = 1)
            val myorder1 = DraftOrder(email = userEmail, note = "fav", note_attributes = item_Image1, line_items = listOf(lineItem1))
            val mylist = CartModel(myorder1)

            val retrofitService = RetrofitService.getInstance()
            val mainRepository = CategoryRepository(retrofitService)

            viewModel = ViewModelProvider(this, CategoryViewModelFactory(mainRepository)).get(CategoryViewModel::class.java)
            viewModel.postFavorite(mylist)
            viewModel.postFavorite.observe(viewLifecycleOwner, {

                if (it == null) {
                  //  Toast.makeText(requireContext(),"failed to add to favorite", Toast.LENGTH_LONG).show()
                } else {

                 //   Toast.makeText(requireContext(), "added sucessfully", Toast.LENGTH_LONG).show()
                }
            })
        }

    }

    override fun OnRemoveFromFavoriteButtonClickListener(data: Product) {


        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs", Context.MODE_PRIVATE)
        var userEmail: String = sharedPreferences.getString("EMAIL_LOGIN", "").toString()

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = CategoryRepository(retrofitService)

        // get all product -> filter by productid -> if exist -> draftorderid -> draft order id
        var productId = "${data.id}"
        Log.i("TAG", "OnRemoveFromFavoriteButtonClickListener: ${productId}")
        var draftOrderId = ""


        viewModel = ViewModelProvider(this, CategoryViewModelFactory(mainRepository)).get(CategoryViewModel::class.java)
        viewModel.getFavoriteProducts()
        viewModel.favoriteProducts.observe(viewLifecycleOwner, {
            var arr = (it.draft_orders.filter { it.email == userEmail && it.note == "fav" })  // [0].line_items?.get(0)?.product_id.toString()
            Log.i("TAG", "OnRemoveFromFavoriteButtonClickListener: ${arr.count()}")

            var ar = mutableSetOf("")
            for ( i in 0..arr.size-1){
                ar.add(arr[i].line_items?.get(0)?.product_id.toString())
            }
            ar.remove(productId)

            Log.i("TAG", "onViewCreatedssssssssssssssssssssssssssssssssssssssssssssssss: ${ar}")
            categoryAdapter.setDraftlist(ar)



            for (i in 0 until arr.size) {
                if (productId in arr[i].line_items?.get(0)?.product_id.toString()) {
                    draftOrderId = arr[i].id.toString()
                    viewModel.deleteProductFromFavorite(draftOrderId)
                    viewModel.deleteFromFavorite.observe(viewLifecycleOwner, {
                        if (it != null) {
                          //  Toast.makeText(requireContext(), "deleted sucssefuly", Toast.LENGTH_SHORT).show()
                        } else {
                          //  Toast.makeText(requireContext(), " cant delete this item ", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        })

        }




}