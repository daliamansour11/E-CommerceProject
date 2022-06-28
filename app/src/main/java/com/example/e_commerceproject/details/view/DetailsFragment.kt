package com.example.e_commerceproject.details.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commerceproject.R
import com.example.e_commerceproject.authentication.login.view.LoginFragment
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.model.LineItem
import com.example.e_commerceproject.cart.model.NoteAttribute
import com.example.e_commerceproject.cart.view.CartFragment
import com.example.e_commerceproject.details.viewmodel.DetailsViewModel
import com.example.e_commerceproject.details.viewmodel.DetailsViewModelFactory
import com.example.e_commerceproject.network.remotesource.DetailsRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService


class DetailsFragment : Fragment() {

    lateinit var detailsaddtofavorieButton: Button
    lateinit var detailsRemoveFromFavorite : Button
    lateinit var addtocartbtn: Button
    lateinit var viewModel: DetailsViewModel
    lateinit var productName: TextView
    lateinit var productPrice: TextView
    lateinit var productDescription: TextView
    lateinit var ratingBar: RatingBar
    var probuctid: Long = 6870134685835
    var data: List<String> = ArrayList()

    var imagearraysize = 4
    var image = "https://cdn.shopify.com/s/files/1/0589/7509/2875/products/85cc58608bf138a50036bcfe86a3a362.jpg?v=1653403067"
    var varientId = 40335555035275
    var productIdd = ""
    var draftOrderId = ""
    val productIdSet  = mutableSetOf("")
    val draftOrderIdSet  = mutableSetOf("")
    lateinit var adapter: ImagesAdapter
    lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs", Context.MODE_PRIVATE)
        var userEmail: String = sharedPreferences.getString("EMAIL_LOGIN", "").toString()

        var args = this.arguments
        probuctid= args?.get("productid") as Long

        adapter = ImagesAdapter(requireContext())

        viewPager = view.findViewById(R.id.images_viewpager2)

        productName = view.findViewById(R.id.producct_name_details)
        productPrice = view.findViewById(R.id.product_price_details)
        productDescription = view.findViewById(R.id.product_description_for_details)
        ratingBar = view.findViewById(R.id.product_ratingbar_details)

        //val productIdSet: Set<String> = HashSet()
        //val draftOrderIdSet: Set<String> = HashSet()
//        productIdSet.add("lk")
//        productIdSet.add("hg")
//        val sharedPreferencess : SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs" ,Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//
//        var productIdSett = sharedPreferencess.getStringSet("productIdSet", null)
//        var draftOrderIdSett = sharedPreferencess.getStringSet("draftOrderIdSet", null)


//
//        var p = "873751937163:6870135275659"
//        val delim = ":"
//
//        val list = p.split(delim)
//        if("6870135275659" in list ){
//            Log.i("TAG", "onViewCreated: irrrrrrrrrrrrrrrrrrrrrrrrrrr ${list[0]}")
//
//        }


        var currency = "EGP"

        var currencyTtpe: String = sharedPreferences.getString("CURRENCY_TYPE_RESULT", "").toString()
        var converterResponse: String = sharedPreferences.getString("CURRENCY_CONVERTER_RESULT", "").toString()

        if( currencyTtpe == "EGP"){
            currency = "EGP"
        }else if ( currencyTtpe == "USD"){
            currency = "USD"
        }

        var r = converterResponse.toDouble()

        Log.i("TAG", "onViewCreated*****************************: ${10*r}")



        val retrofitService = RetrofitService.getInstance()
        val mainRepository = DetailsRepository(retrofitService)

        viewModel = ViewModelProvider(this, DetailsViewModelFactory(mainRepository)).get(DetailsViewModel::class.java)
        viewModel.getProductInfo("${probuctid}")
        viewModel.productInfo.observe(viewLifecycleOwner, {

            Log.i("TAG", "onViewCreated:rrrrrrrrrrrr ${it}")
            imagearraysize = it.product.images.size
            productName.text = it.product.title
            productPrice.text = "${(it.product.variants[0].price).toDouble() * r} ${currency}"
            productDescription.text = it.product.body_html
            ratingBar.rating = (it.product.variants[0].inventory_quantity.toFloat()) / 2

            image = it.product.image.src
            varientId = it.product.variants[0].id
            productIdd = "${it.product.id}"
            //productIdSet.add(productId)


            adapter.setListd(it.product.images)
            adapter.notifyDataSetChanged()

        })
        viewPager.adapter = adapter


        detailsaddtofavorieButton = view.findViewById(R.id.detailsaddtofavorieButton)
        detailsRemoveFromFavorite = view.findViewById(R.id.details_remove_from_favorieButton)

        detailsaddtofavorieButton.visibility = View.VISIBLE
        detailsRemoveFromFavorite.visibility = View.GONE


        if (userEmail == null || userEmail == "") {
            // if no user -> add to fav -> go to login first
            detailsaddtofavorieButton.visibility = View.VISIBLE
            detailsRemoveFromFavorite.visibility = View.GONE

        }else{
            productIdd = ""
            // if product id in draftorders -> detailsRemoveFromFavorite = visible
            // and draftorderid = draftorderid
            viewModel.getFavoriteProducts()
            viewModel.favoriteProducts.observe(viewLifecycleOwner, {
                var arr = (it.draft_orders.filter { it.email ==  userEmail && it.note == "fav" })  // [0].line_items?.get(0)?.product_id.toString()
                for (i in 0 until arr.size){
                    if (productIdd in arr[i].line_items?.get(0)?.product_id.toString()  ){
                        draftOrderId = arr[i].id.toString()
                        detailsaddtofavorieButton.visibility = View.GONE
                        detailsRemoveFromFavorite.visibility = View.VISIBLE
                    }
               }




            })
        }


        detailsaddtofavorieButton.setOnClickListener {


            if (userEmail == null || userEmail == "") {
                // navigate to login screen
                Toast.makeText(requireContext(), "you must login or register first", Toast.LENGTH_SHORT).show()
                val loginFragment = LoginFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, loginFragment)?.commit()

            } else {

                Log.i("TAG", "onViewCreated image : ${image}")
                var item_Image1 = listOf<NoteAttribute>(NoteAttribute("image", image))

                var lineItem1 = LineItem(variant_id = varientId, quantity = 1)
                val myorder1 = DraftOrder(email = userEmail, note = "fav", note_attributes = item_Image1, line_items = listOf(lineItem1))
                val mylist = CartModel(myorder1)

                viewModel.postFavorite(mylist)
                viewModel.postFavorite.observe(viewLifecycleOwner, {

                    if (it == null) {
                        Toast.makeText(requireContext(),"failed to add to favorite", Toast.LENGTH_LONG).show()
                    } else {
                        draftOrderId = "${it.draft_order?.id}"
//                        draftOrderIdSett?.add("${draftOrderId}")
//                        productIdSett?.add(productId)
//                        editor.putStringSet("draftOrderIdSet", draftOrderIdSett).apply()
//                        editor.putStringSet("productIdSet", productIdSett).apply()

                        Toast.makeText(requireContext(), "added sucessfully", Toast.LENGTH_LONG).show()
                        detailsaddtofavorieButton.visibility = View.GONE
                        detailsRemoveFromFavorite.visibility = View.VISIBLE

                    }
                })
            }
        }

        detailsRemoveFromFavorite.setOnClickListener {

            viewModel.deleteProductFromFavorite(draftOrderId)
            viewModel.deleteFromFavorite.observe(viewLifecycleOwner, {
                if(it != null){
                    Toast.makeText(requireContext() , "deleted sucssefuly" , Toast.LENGTH_SHORT).show()
                    detailsaddtofavorieButton.visibility = View.VISIBLE
                    detailsRemoveFromFavorite.visibility = View.GONE
                }else{
                    Toast.makeText(requireContext() , " cant delete this item " , Toast.LENGTH_SHORT).show()
                }
            })

        }

        addtocartbtn = view.findViewById(R.id.addtocartbtn)
        addtocartbtn.setOnClickListener {

           if (userEmail == null || userEmail == "") {
                // navigate to login screen
                Toast.makeText(requireContext(), "you must login or register first",Toast.LENGTH_SHORT).show()
                val loginFragment = LoginFragment()
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentContainerView, loginFragment)?.commit()
            } else {
                var item_Image2 = listOf<NoteAttribute>(NoteAttribute("image", image))

//                var item_Image = listOf<NoteAttribute>(
//                    NoteAttribute(
//                        "image",
//                        "https://cdn.shopify.com/s/files/1/0589/7509/2875/products/85cc58608bf138a50036bcfe86a3a362.jpg?v=1653403067"
//  ))

                var lineItem = LineItem( variant_id = varientId, quantity = 1)
                val myorder = DraftOrder(email = userEmail,//reham33@gmail.com
                     note = "cart", note_attributes = item_Image2, line_items = listOf(lineItem) )
                val mylist = CartModel(myorder)
                viewModel.pushPost(mylist)
                viewModel.mRCartResonse.observe(viewLifecycleOwner, {
                    if (it == null) {
                        Toast.makeText(requireContext(), "failed to post", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(requireContext(), "sucessfull post", Toast.LENGTH_LONG).show()
                    }
                })

            }
        }

    }

}
