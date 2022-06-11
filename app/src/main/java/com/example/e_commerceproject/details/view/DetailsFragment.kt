package com.example.e_commerceproject.details.view

import android.net.Uri
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
import com.example.e_commerceproject.data.remotesource.DetailsRepository
import com.example.e_commerceproject.data.remotesource.RetrofitService
import com.example.e_commerceproject.details.viewmodel.DetailsViewModel
import com.example.e_commerceproject.details.viewmodel.DetailsViewModelFactory
import java.net.URL


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

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = DetailsRepository(retrofitService)

        viewModel = ViewModelProvider(this, DetailsViewModelFactory(mainRepository)).get(DetailsViewModel::class.java)
        viewModel.getProductInfo("${probuctid}")
        viewModel.productInfo.observe(viewLifecycleOwner,  {
            Log.d("TAG", "inside observe")
            Log.i("TAG", "onViewCreated:rrrrrrrrrrrr ${it}")
              imagearraysize = it.product.images.size

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

        viewPager.adapter = adapter

        detailsaddtofavorieButton = view.findViewById(R.id.detailsaddtofavorieButton)
        detailsaddtofavorieButton.setOnClickListener {
            Toast.makeText(requireContext() , "iu" , Toast.LENGTH_SHORT).show()
        }

        addtocartbtn = view.findViewById(R.id.addtocartbtn)
        addtocartbtn.setOnClickListener {
            Toast.makeText(requireContext() , "po" , Toast.LENGTH_SHORT).show()
        }
    }

}