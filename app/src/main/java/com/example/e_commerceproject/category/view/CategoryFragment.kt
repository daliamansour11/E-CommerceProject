package com.example.e_commerceproject.category.view

import android.content.Context
import android.content.SharedPreferences
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
import com.example.e_commerceproject.authentication.login.view.LoginFragment
import com.example.e_commerceproject.Settings.view.SettingsFragment
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.model.LineItem
import com.example.e_commerceproject.cart.model.NoteAttribute
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
        }else{
            brandId =  args?.get("BRAND_ID") as String
            Log.i("TAG", "onViewCreatedmmmmmmmmmmmmmmmmmmmmmmm: ${brandId}")
        }
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
                    Toast.makeText(requireContext(),"failed to add to favorite", Toast.LENGTH_LONG).show()
                } else {

                    Toast.makeText(requireContext(), "added sucessfully", Toast.LENGTH_LONG).show()
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

            for (i in 0 until arr.size) {
                if (productId in arr[i].line_items?.get(0)?.product_id.toString()) {
                    draftOrderId = arr[i].id.toString()
                    viewModel.deleteProductFromFavorite(draftOrderId)
                    viewModel.deleteFromFavorite.observe(viewLifecycleOwner, {
                        if (it != null) {
                            Toast.makeText(requireContext(), "deleted sucssefuly", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), " cant delete this item ", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        })

        }




}