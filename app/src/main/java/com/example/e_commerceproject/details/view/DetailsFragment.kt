package com.example.e_commerceproject.details.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commerceproject.R
import com.example.e_commerceproject.authentication.login.view.LoginFragment
import com.example.e_commerceproject.cart.model.*
import com.example.e_commerceproject.cart.view.CartFragment
import com.example.e_commerceproject.network.remotesource.DetailsRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.example.e_commerceproject.details.viewmodel.DetailsViewModel
import com.example.e_commerceproject.details.viewmodel.DetailsViewModelFactory


class DetailsFragment : Fragment() {

    lateinit var detailsaddtofavorieButton: Button
    lateinit var addtocartbtn: Button
    lateinit var viewModel: DetailsViewModel
    lateinit var productName : TextView
    lateinit var productPrice: TextView
    lateinit var productDescription: TextView
    lateinit var ratingBar : RatingBar
    var probuctid : Long = 6870134685835
    var data:List<String> = ArrayList()
//    lateinit var imgSwitcher : ImageSwitcher
    var imagearraysize = 4
    private var index = 0
    val imagearray = arrayOfNulls<String>(imagearraysize)
    var image = "https://cdn.shopify.com/s/files/1/0589/7509/2875/products/85cc58608bf138a50036bcfe86a3a362.jpg?v=1653403067"
    var varientId = 40335555035275
    lateinit var adapter : ImagesAdapter
    lateinit var viewPager : ViewPager2



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

        val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs" , Context.MODE_PRIVATE)
        var userEmail : String  = sharedPreferences.getString("EMAIL_LOGIN" , "").toString()



        var args = this.arguments
        probuctid = args?.get("productid") as Long

        // imgSwitcher = view.findViewById(R.id.details_image_switcher)

//        imgSwitcher?.setFactory({
//            val imgView = ImageView(requireContext())
//            imgView.scaleType = ImageView.ScaleType.FIT_CENTER
//            imgView
//        })

        adapter = ImagesAdapter(requireContext())


        viewPager = view.findViewById(R.id.images_viewpager2)

        productName = view.findViewById(R.id.producct_name_details)
        productPrice = view.findViewById(R.id.product_price_details)
        productDescription = view.findViewById(R.id.product_description_for_details)
        ratingBar = view.findViewById(R.id.product_ratingbar_details)

        ratingBar.isClickable = false



        val retrofitService = RetrofitService.getInstance()
        val mainRepository = DetailsRepository(retrofitService)

        viewModel = ViewModelProvider(this, DetailsViewModelFactory(mainRepository)).get(DetailsViewModel::class.java)
        viewModel.getProductInfo("${probuctid}")
        viewModel.productInfo.observe(viewLifecycleOwner,  {
            Log.d("TAG", "inside observe")
            Log.i("TAG", "onViewCreated:rrrrrrrrrrrr ${it}")
            imagearraysize = it.product.images.size
            productName.text = it.product.title
            productPrice.text = "${it.product.variants[0].price} $"
            productDescription.text = it.product.body_html
            ratingBar.rating = (it.product.variants[0].inventory_quantity.toFloat())/2

            image = it.product.image.src
            varientId = it.product.variants[0].id

            adapter.setListd(it.product.images)
            adapter.notifyDataSetChanged()

           // ratingBar.set
           // adapter.setListd(list)

//            val prev = view.findViewById<Button>(R.id.details_image_prev_obtn)
//            prev.setOnClickListener {
//                index = if (index - 1 >= 0) index - 1 else 2
//                imgSwitcher?.setImageResource(flowers[index])
//            }
//            // next button functionality
//            val next = view.findViewById<Button>(R.id.details_image_next_obtn)
//            next.setOnClickListener {
//                index = if (index + 1 < flowers.size) index +1 else 0
//                imgSwitcher?.setImageResource(flowers[index])
//            }

            Log.i("TAG", "onViewCreated: $imagearraysize")
        })

        var item_Image = listOf<NoteAttribute>(NoteAttribute("image","https://cdn.shopify.com/s/files/1/0589/7509/2875/products/85cc58608bf138a50036bcfe86a3a362.jpg?v=1653403067"))

        var lineItem = LineItem(
            variant_id = 40335555395723 ,
            quantity = 1
        )
        val myorder = DraftOrder(
            email = "reham33@gmail.com",
            note = "card",
          note_attributes = item_Image ,
            line_items = listOf(lineItem)
        )
        val mylist = CartModel(
           myorder
        )
        viewModel.pushPost(mylist)



        viewPager.adapter = adapter

        detailsaddtofavorieButton = view.findViewById(R.id.detailsaddtofavorieButton)
        detailsaddtofavorieButton.setOnClickListener {

            if(userEmail == null || userEmail == ""){
              // navigate to login screen
                Toast.makeText(requireContext() , "you must login or register first" , Toast.LENGTH_SHORT).show()
                val loginFragment = LoginFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, loginFragment)?.commit()

            }else{
                Log.i("TAG", "onViewCreated image : ${image}")
                var item_Image1 = listOf<NoteAttribute>(NoteAttribute("image",image))

                var lineItem1 = LineItem(
                    variant_id = varientId ,
                    quantity = 1
                )
                val myorder1 = DraftOrder(
                    email = userEmail,
                    note = "fav",
                    note_attributes = item_Image1 ,
                    line_items = listOf(lineItem1)
                )
                val mylist = CartModel(myorder1)
                viewModel.postFavorite(mylist)
                viewModel.postFavorite.observe(viewLifecycleOwner, {
                    System.out.println("We are in productInfoobjectobserver")

                    if (it == null) {
                        Toast.makeText(requireContext(), "failed to add to favorite", Toast.LENGTH_LONG).show()
                    } else {
                        Log.i("TAG", "onViewCreatedppppppppppppppppppppppppp: ${it.draft_order}")
                        Toast.makeText(requireContext(), "${it.draft_order.id}", Toast.LENGTH_LONG).show()
                        Toast.makeText(requireContext(), "added sucessfully", Toast.LENGTH_LONG).show()
                    }
                })
            }
   }

        addtocartbtn = view.findViewById(R.id.addtocartbtn)
        addtocartbtn.setOnClickListener {

            val cart_fragment = CartFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, cart_fragment)
                ?.commit()
            Toast.makeText(requireContext(), "po", Toast.LENGTH_SHORT).show()
//            var lineItemList = listOf<LineItem>(LineItem(0, "","","",2,40335550546059))

            var item_Image = listOf<NoteAttribute>(NoteAttribute("image",
                "https://cdn.shopify.com/s/files/1/0589/7509/2875/products/85cc58608bf138a50036bcfe86a3a362.jpg?v=1653403067"))

            var lineItem = LineItem(
                variant_id = 40335555395723 ,
                quantity = 1
            )
            val myorder = DraftOrder(
                email = "reham33@gmail.com",
                note = "card",
                note_attributes = item_Image ,
                line_items = listOf(lineItem)

            )
            val mylist = CartModel(
                myorder
            )
            viewModel.pushPost(mylist)
            viewModel.mRCartResonse.observe(viewLifecycleOwner, {
                System.out.println("We are in productInfoobjectobserver")
                if (it == null) {
                    Toast.makeText(requireContext(), "failed to post", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "sucessfull post", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }

}


