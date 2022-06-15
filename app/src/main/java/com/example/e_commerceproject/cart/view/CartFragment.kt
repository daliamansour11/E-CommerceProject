package com.example.e_commerceproject.cart.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.payment.view.PaymentFragment
import com.example.e_commerceproject.R
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.viewmodel.CartViewModel
import com.example.e_commerceproject.cart.viewmodel.CartViewModelFactory
import com.example.e_commerceproject.network.remotesource.CartRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService


import com.example.e_commerceproject.home.model.DummyData
//import com.example.e_commerceproject.common.model.DummyData


class CartFragment : Fragment() {

    lateinit var cartList: CartModel
    lateinit var recyclerView: RecyclerView
    lateinit var cartAdapter: CartAdapter

    //lateinit var cartFragmentView: View
    lateinit var addressArrow: ImageView
    lateinit var payButtonCart: Button
    lateinit var viewModel: CartViewModel
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
        payButtonCart = view.findViewById(R.id.payButtonCart)
        recyclerView = view.findViewById(R.id.cartRecyclerView)
        val cartLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        cartLinearLayoutManager.orientation = RecyclerView.VERTICAL
        cartAdapter = CartAdapter(requireContext(), this)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = CartRepository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            CartViewModelFactory(mainRepository)
        ).get(CartViewModel::class.java)
        recyclerView.layoutManager = cartLinearLayoutManager
        recyclerView.adapter = cartAdapter
        viewModel.getCart()
        viewModel.cart_Response.observe(viewLifecycleOwner, {
            Log.d("TAG", "inside cartfragment")
            Log.i("TAG", "onViewCreated:rrrrrrTTTTTTTTTrrrrrr ${it}")
            cartList = it
            it.draft_order.line_items?.let { it1 ->
                cartAdapter.setlist(it.draft_order)
            }
            cartAdapter.notifyDataSetChanged()
        })


        addressArrow = view.findViewById(R.id.shoppingCartArrowBack)
        addressArrow.setOnClickListener(View.OnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            //  fragmentTransaction.replace(R.id.,fragment)
            fragmentTransaction.commit()
        })
        payButtonCart.setOnClickListener {
            val paymentFragment = PaymentFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, paymentFragment)?.commit()

        }

    }

    fun upgateCart(data: DraftOrder) {
            viewModel.updateCart(data)
    }


}