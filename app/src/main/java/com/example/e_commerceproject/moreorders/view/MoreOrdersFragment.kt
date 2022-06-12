package com.example.e_commerceproject.moreorders.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.profile.model.OrderModel
import com.example.e_commerceproject.profile.view.ProfileFragment
import com.google.gson.Gson


class MoreOrdersFragment : Fragment() {

    lateinit var orderPrice: TextView
    lateinit var orderDate: TextView
    lateinit var backArrow: ImageView
    lateinit var moreOrdersFragmentView: View
    lateinit var moreOrderBack: ImageView
    lateinit var orderList:ArrayList<OrderModel>
    lateinit var recyclerView: RecyclerView
    lateinit var moreOrdersAdapter: MoreOrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {bundle->
            orderList = Gson().fromJson(bundle.getString("orders"), ArrayList::class.java)
                    as ArrayList<OrderModel>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var moreOrders =   inflater.inflate(R.layout.fragment_more_orders, container, false)
        moreOrderBack = moreOrders.findViewById(R.id.moreOrdersArrowBack)
        return  moreOrders
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.moreOrdersRecyclerView)
        val homeSearchLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        homeSearchLinearLayoutManager.orientation= RecyclerView.VERTICAL
        recyclerView.layoutManager=homeSearchLinearLayoutManager
        moreOrdersAdapter = MoreOrderAdapter(requireContext())
        recyclerView.adapter=moreOrdersAdapter

        moreOrderBack.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                Toast.makeText(context, "go to profile ", Toast.LENGTH_LONG).show()
                var bundle = Bundle()
                val df = ProfileFragment()
                df.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, df)
                    ?.commit()
                // Navigation.findNavController(requireView()).navigate(R.id.action_favoriteFragment2_to_favoriteDetailsFragment)
            }


        })
    }


}