package com.example.e_commerceproject.cart.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.payment.view.PaymentFragment
import com.example.e_commerceproject.R
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.viewmodel.CartViewModel
import com.example.e_commerceproject.cart.viewmodel.CartViewModelFactory
import com.example.e_commerceproject.network.remotesource.CartRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.google.android.material.snackbar.Snackbar

//import com.example.e_commerceproject.home.model.DummyData

//import com.example.e_commerceproject.home.model.DummyData
//import com.example.e_commerceproject.common.model.DummyData


class CartFragment : Fragment() {
    lateinit var cartList: CartModel
    lateinit var recyclerView: RecyclerView
    lateinit var cartAdapter: CartAdapter
    val mlist = ArrayList<CartModel>()


    lateinit var total_price :TextView
    //lateinit var cartFragmentView: View
    lateinit var addressArrow: ImageView
    lateinit var payButtonCart: Button
    lateinit var viewModel: CartViewModel
    var cart_id :Long = 5764286480523
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//

        // Inflate the layout for this fragment
        var cart_frag =  inflater.inflate(R.layout.fragment_cart, container, false)

        total_price  = cart_frag.findViewById(R.id.textView)
        return  cart_frag
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        payButtonCart = view.findViewById(R.id.payButtonCart)

        recyclerView = view.findViewById(R.id.cartRecyclerView)
        val cartLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        cartLinearLayoutManager.orientation = RecyclerView.VERTICAL
        cartAdapter = CartAdapter(requireContext(), this)
           swipeToDelete()

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

                cartAdapter.setlist(it.draft_orders)
                total_price.text= it.draft_orders[0].subtotal_price
                cartAdapter.notifyDataSetChanged()

            })


//        viewModel.getTotalPrice().observe(viewLifecycleOwner,{
//            total_price = view.findViewById(R.id.total_price)
        //   total_price.("")


//        })


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
//
    fun upgateCart(data: CartModel) {
        viewModel.updateCart(data)
    }



private fun swipeToDelete(){
    ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true

        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val  postion = viewHolder.adapterPosition
            val item = cartAdapter.data1[postion]
                   mlist.removeAt(postion)
            cartAdapter.notifyItemRemoved(postion)
          //  upgateCart()
            Snackbar.make(
             viewHolder.itemView.findViewById(R.id.cart_constraint),
            "item ${item}",
                Snackbar.LENGTH_SHORT
            ).apply {
                setAction("Undo"){
///    mlist.add(item)
                }
            }


                .show()
           // upgateCart(mlist.trimToSize().toString())


        }

    }).attachToRecyclerView(recyclerView)
}

}




