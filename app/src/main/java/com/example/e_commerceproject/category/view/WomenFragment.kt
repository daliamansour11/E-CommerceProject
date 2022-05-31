package com.example.e_commerceproject.category.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.category.model.CategoriesModel
import com.example.e_commerceproject.category.view.adapters.CategoryAdapter
import com.example.e_commerceproject.details.view.DetailsFragment

class WomenFragment : Fragment() , OnProductClickInterface{
    private lateinit var  categoryAdapter: CategoryAdapter
    private var dataList = mutableListOf<CategoriesModel>()
    lateinit var recyclerView : RecyclerView;

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

        //initiate the grid  view
        //in this case I make row grid in a row
        //if you want to change that change the number

        recyclerView = view.findViewById(R.id.womenCategoryRecycleview)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        categoryAdapter = CategoryAdapter(requireContext() , this)
        recyclerView.adapter = categoryAdapter

        //add data
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))
        dataList.add(CategoriesModel("Title" , R.drawable.online , R.drawable.favorite_24))

        categoryAdapter.setDataList(dataList)


//        recyclerView.setOnClickListener {
//            val df = DetailsFragment()
//            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, df)?.commit()
//        }

    }

    override fun onProductClick(model: CategoriesModel) {
        Toast.makeText(requireContext() , "iui" , Toast.LENGTH_SHORT).show()
        val detailsfragment = DetailsFragment()
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, detailsfragment)?.commit()

    }

}