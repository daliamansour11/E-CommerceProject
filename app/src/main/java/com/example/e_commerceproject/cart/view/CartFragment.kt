package com.example.e_commerceproject.cart.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.home.model.DummyData

class CartFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var cartAdapter: CartAdapter
    //lateinit var cartFragmentView: View
    lateinit var addressArrow: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=view.findViewById(R.id.cartRecyclerView)
        val cartLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        cartLinearLayoutManager.orientation= RecyclerView.VERTICAL
        cartAdapter= CartAdapter(requireContext())
        cartAdapter.setDataList(DummyData.PRODUCT_DATA)
        recyclerView.layoutManager=cartLinearLayoutManager
        recyclerView.adapter=cartAdapter

        addressArrow= view.findViewById(R.id.shoppingCartArrowBack)
        addressArrow.setOnClickListener(View.OnClickListener {
            val fragmentManager=parentFragmentManager
            val fragmentTransaction=fragmentManager.beginTransaction()

            //  fragmentTransaction.replace(R.id.,fragment)
            fragmentTransaction.commit()
        })


    }
}