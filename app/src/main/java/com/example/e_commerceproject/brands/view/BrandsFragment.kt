package com.example.e_commerceproject.brands.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.brands.model.BrandModel
import com.example.e_commerceproject.details.view.DetailsFragment
import com.example.e_commerceproject.home.view.HomeFragment


class BrandsFragment : Fragment(),OnBrandClicked {
    lateinit var brand_back : Button
    private lateinit var  brandadapter: BrandsAdapter
    private var dataList = mutableListOf<com.example.e_commerceproject.home.model.BrandModel>()
    lateinit var recyclerView : RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var brand_frag =  inflater.inflate(R.layout.fragment_brands, container, false)
        brand_back = brand_frag.findViewById(R.id.back_arrow)
        return brand_frag
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initiate the grid  view
        //in this case I make row grid in a row
        //if you want to change that change the number

        recyclerView = view.findViewById(R.id.brands_recyclerview)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        brandadapter = BrandsAdapter(requireContext() , this)
        recyclerView.adapter = brandadapter



        //add data
        dataList.add(com.example.e_commerceproject.home.model.BrandModel("1234", R.drawable.online))
        dataList.add(com.example.e_commerceproject.home.model.BrandModel("1234", R.drawable.online))
        dataList.add(com.example.e_commerceproject.home.model.BrandModel("1234", R.drawable.online))
        dataList.add(com.example.e_commerceproject.home.model.BrandModel("1234", R.drawable.online))
        dataList.add(com.example.e_commerceproject.home.model.BrandModel("1234", R.drawable.online))
        dataList.add(com.example.e_commerceproject.home.model.BrandModel("1234", R.drawable.online))
        dataList.add(com.example.e_commerceproject.home.model.BrandModel("1234", R.drawable.online))

        brandadapter.setDataList(dataList)

        brand_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                Toast.makeText(context, "go to profile ", Toast.LENGTH_LONG).show()
                var bundle = Bundle()
                val df = HomeFragment ()
                df.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, df)?.commit()
                // Navigation.findNavController(requireView()).navigate(R.id.action_favoriteFragment2_to_favoriteDetailsFragment)
            }


        })
    }
    override fun onbrandClicked() {
//        var bundle = Bundle()
//        model.branname?.let { bundle.putString("latitude", it) }
//        model.id?.let { bundle.putInt("120", it) }
//        Toast.makeText(requireContext(),"bhlknlask",Toast.LENGTH_LONG).show()

        val detailsFragment = DetailsFragment()
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, detailsFragment)?.commit()
        Toast.makeText(requireContext(),"bhlknlask",Toast.LENGTH_LONG).show()


        //     val df = ()
//        df.arguments = bundle
//        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, df)?.commit()
//        // Navigation.findNavController(requireView()).navigate(R.id.action_favoriteFragment2_to_favoriteDetailsFragment)

    }
}