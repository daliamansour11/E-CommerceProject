package com.example.e_commerceproject.moreorders.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.orderdetails.view.OrdersDetailsFragment
import com.example.e_commerceproject.profile.model.OrderModel
import com.example.e_commerceproject.profile.view.ProfileFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MoreOrdersFragment : Fragment(), OnOrderClickListener {
    lateinit var moreorder_back: ImageView
    lateinit var orderList:ArrayList<OrderModel>
    lateinit var recyclerView: RecyclerView
    lateinit var moreOrdersAdapter: MoreOrdersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {bundle->
            class Token : TypeToken<ArrayList<OrderModel>>()
            orderList = Gson().fromJson(bundle.getString("orders"), Token().type)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var moreOrders =   inflater.inflate(R.layout.fragment_more_orders, container, false)
        moreorder_back = moreOrders.findViewById(R.id.moreOrdersArrowBack)
        return  moreOrders
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.moreOrdersRecyclerView)
        val homeSearchLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        homeSearchLinearLayoutManager.orientation= RecyclerView.VERTICAL
        recyclerView.layoutManager=homeSearchLinearLayoutManager
        moreOrdersAdapter = MoreOrdersAdapter(requireContext(), this)
        recyclerView.adapter=moreOrdersAdapter
        moreOrdersAdapter.setDataList(orderList)
        moreOrdersAdapter.notifyDataSetChanged()

        moreorder_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                var bundle = Bundle()
                val df = ProfileFragment()
                df.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, df)
                    ?.commit()
                // Navigation.findNavController(requireView()).navigate(R.id.action_favoriteFragment2_to_favoriteDetailsFragment)
            }


        })
    }

    override fun onMoreOrderClicked(order: OrderModel) {
        val ordersDetailsFragment = OrdersDetailsFragment()
        val bundle = Bundle()

        bundle.putString("orders", Gson().toJson(orderList))
        bundle.putString("order", Gson().toJson(order))
        ordersDetailsFragment.setArguments(bundle)
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, ordersDetailsFragment)?.commit()
    }
}