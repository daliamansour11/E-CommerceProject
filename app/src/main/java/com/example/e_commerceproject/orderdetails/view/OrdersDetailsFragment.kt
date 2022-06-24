package com.example.e_commerceproject.orderdetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.moreorders.view.MoreOrdersFragment
import com.example.e_commerceproject.profile.model.OrderModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class OrdersDetailsFragment : Fragment() {

    lateinit var backArrow: ImageView
    lateinit var orderDate: TextView
    lateinit var customerName: TextView
    lateinit var order: OrderModel
    lateinit var orderList:ArrayList<OrderModel>
    lateinit var recyclerView: RecyclerView
    lateinit var orderDetailsAdapter: OrderDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {bundle->
            class Token : TypeToken<ArrayList<OrderModel>>()
            orderList = Gson().fromJson(bundle.getString("orders"), Token().type)
            order = Gson().fromJson(bundle.getString("order"), OrderModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var orderDetails =  inflater.inflate(R.layout.fragment_orders_details, container, false)
        backArrow = orderDetails.findViewById(R.id.orderDetailsArrowBack)
        orderDate = orderDetails.findViewById(R.id.orderDateOrderDetailsId)
        customerName = orderDetails.findViewById(R.id.customerNameOrderDetailsId)
        return  orderDetails
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.orderDetailsRecyclerView)
        val homeSearchLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        homeSearchLinearLayoutManager.orientation= RecyclerView.VERTICAL
        recyclerView.layoutManager=homeSearchLinearLayoutManager
        orderDetailsAdapter = OrderDetailsAdapter(requireContext())
        recyclerView.adapter=orderDetailsAdapter
        orderDetailsAdapter.setDataList(order.line_items)
        orderDetailsAdapter.notifyDataSetChanged()

        customerName.text = order.customer.first_name + " " + order.customer.last_name
        var createdAt = order.created_at.split("T")[0]
        orderDate.text = createdAt

        backArrow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                var bundle = Bundle()
                val df = MoreOrdersFragment()
                bundle.putString("orders", Gson().toJson(orderList))
                df.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, df)
                    ?.commit()
            }


        })
    }

}