package com.example.e_commerceproject.orderdetails.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.address.view.viewModel.AddressViewModel
import com.example.e_commerceproject.address.view.viewModel.AddressViewModelFactory
import com.example.e_commerceproject.moreorders.view.MoreOrdersFragment
import com.example.e_commerceproject.network.remotesource.AdressRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.example.e_commerceproject.profile.model.OrderModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class OrdersDetailsFragment : Fragment() {

    lateinit var backArrow: ImageView
    lateinit var orderDate: TextView
    lateinit var customerName: TextView
    lateinit var customerAddress: TextView
    lateinit var order: OrderModel
    lateinit var orderList:ArrayList<OrderModel>
    lateinit var recyclerView: RecyclerView
    lateinit var orderDetailsAdapter: OrderDetailsAdapter
    lateinit var viewModel: AddressViewModel


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
        customerAddress = orderDetails.findViewById(R.id.customerAddressOrderDetailsId1)

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

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = AdressRepository(retrofitService)
        var customerId: String = "5775923609739"
        viewModel = ViewModelProvider(this, AddressViewModelFactory(mainRepository)).get(AddressViewModel::class.java)
        viewModel.getAddress(customerId)
        viewModel.getCustomerAddresses.observe(viewLifecycleOwner, {

            customerAddress.text = "${it.addresses[0].address1}  ${it.addresses[0].country}  ${it.addresses[0].city} "

            Log.d("TAG", "inside addresss2fragment")
            Log.i("TAG", "onViewCreated:rrrrrrTTTTTTTTTrrrrrr ${it}")
        })


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