package com.example.e_commerceproject.cart.view

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.viewmodel.CartViewModel
import com.example.e_commerceproject.cart.viewmodel.CartViewModelFactory
import com.example.e_commerceproject.currencyConverter.view.CURRUNEY_TYPE
import com.example.e_commerceproject.currencyConverter.view.SHARD_NAME
import com.example.e_commerceproject.currencyConverter.viewModel.ConverterViewModel
import com.example.e_commerceproject.currencyConverter.viewModel.ConverterViewModelFactory
import com.example.e_commerceproject.network.ConverterRepository
import com.example.e_commerceproject.network.remotesource.CartRepository
import com.example.e_commerceproject.network.remotesource.ConverterApiService
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.example.e_commerceproject.payment.view.CashFragment
import com.example.e_commerceproject.payment.view.PaymentFragment

class CartFragment : Fragment() ,OnDeleteFromCartListener,OnPayClickListener {
    lateinit var cartList: CartModel
    lateinit var recyclerView: RecyclerView
    lateinit var cartAdapter: CartAdapter
    val mlist = ArrayList<DraftOrder>()
    lateinit var CviewModel: ConverterViewModel
    lateinit var VFactory: ConverterViewModelFactory
    lateinit var sharedPref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var total_price: TextView

    //lateinit var cartFragmentView: View
    lateinit var addressArrow: ImageView
    lateinit var payButtonCart: Button
    lateinit var viewModel: CartViewModel


    var currecncy: String? = null
//    var cart_id: String = "873008693387"
    var to: String = "USD"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPref = requireActivity().getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
        val sharedPref = requireActivity().getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)
        val str_name = sharedPref.getString(CURRUNEY_TYPE, "")


        if (currecncy == "EGP") {
            to = "EGP"
            Toast.makeText(requireContext(), "we are using EGP", Toast.LENGTH_SHORT).show()
        } else if(currecncy == "USD"){
            to = "USD"
            Toast.makeText(requireContext(), "we are using USD", Toast.LENGTH_SHORT).show()

            val retrofitService = ConverterApiService.getInstance()
            val mainRepository = ConverterRepository(retrofitService)
            CviewModel = ViewModelProvider(this, ConverterViewModelFactory(mainRepository)).get(ConverterViewModel::class.java)

            CviewModel.getcontvertedResponse("PonwHXimsWL7N3LyigLfHj3E1Rrj0V9R" ,"USD" , "5" , "EGP")
            CviewModel._Convert_Response.observe(viewLifecycleOwner) { respo ->
                 Log.i(ContentValues.TAG, "onChanged: ${respo.result}")
                System.out.println("Re" + respo.result)
            }
        }
        // Inflate the layout for this fragment
        var cart_frag = inflater.inflate(R.layout.fragment_cart, container, false)

        total_price = cart_frag.findViewById(R.id.textView3)
        return cart_frag
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        payButtonCart = view.findViewById(R.id.payButtonCart)

        recyclerView = view.findViewById(R.id.cartRecyclerView)
        val cartLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        cartLinearLayoutManager.orientation = RecyclerView.VERTICAL
        cartAdapter = CartAdapter(requireContext(), this , this )
        //swipeToDelete()

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
            var price = 0.0
            val cartList: MutableList<DraftOrder> = mutableListOf()
            for (i in 0..it.draft_orders.size - 1) {
                val sharedPreferences: SharedPreferences =
                    requireContext().getSharedPreferences("loginsharedprefs", Context.MODE_PRIVATE)
                var userEmail: String = sharedPreferences.getString("EMAIL_LOGIN", "").toString()
                Log.i("UserEMAIL", "onViewCreated: email======================" + userEmail)

                if (it.draft_orders.get(i).email == "reham33@gmail.com"
//                    it.draft_orders.get(i).note == "cart"
                ) {
                    cartList.add(it.draft_orders.get(i))
                    price += it.draft_orders[i].subtotal_price?.toDouble() ?: 0.0
                }

                //cartAdapter.setlist(cartList)
                /*    for (i in 0..it.draft_orders.size) {
                        val items_price: Int =
    //                           it.draft_orders[0].line_items?.get(0)?.price * it.draft_orders[0].line_items?.get(0)?.price
    //                       total_price.text=("INR " + items_price + "Rs")
    //                       total_price += total_price + items_price;
                            Log.d("TAG", "bind: Toatal = ${total_price}otalPrice")
    //                       var total : Long = it.draft_orders[0].subtotal_price "*" it.draft_orders.size
    //                       total_price.text= total.toString()

                    }*/
//                       total_price.text= it.draft_orders[0].subtotal_price
                /*cartAdapter.setlist(it.draft_orders)

                total_price.text = it.draft_orders[0].subtotal_price

                cartAdapter.notifyDataSetChanged()*/

                //  }

            }
            Log.i(
                "TAGTAGTAG",
                "onViewCreated: ========================================" + cartList.size
            )
            cartAdapter.setlist(cartList)
            total_price.text = price.toString()
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
            var bundle = Bundle()
            val paymentFragment = PaymentFragment()
            paymentFragment.arguments = bundle


            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, paymentFragment)?.commit()
        }
    }

    fun upgateCart(cartid: String, data: CartModel) {
        viewModel.updateCart(cartid, data)
    }

//    fun deleteitem(cartid: String) {
//        viewModel.deleteCart(cart_id)
//    }

    override fun onDeleteFromFavClicked(id: String) {
        Toast.makeText(requireContext() , "kk" , Toast.LENGTH_SHORT).show()

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = CartRepository(retrofitService)
        viewModel = ViewModelProvider(this, CartViewModelFactory(mainRepository)).get(CartViewModel::class.java)
        viewModel.deleteCartProduct(id)

        viewModel.deleteCart_Res.observe(viewLifecycleOwner, {

            if(it != null){
                Toast.makeText(requireContext() , "deleted sucssefuly" , Toast.LENGTH_SHORT).show()

//                viewModel.getCart()
//
//                viewModel.cart_Response.observe(viewLifecycleOwner, {
//
//                    CartAdapter.(it.draft_orders)
//                    CartAdapter.notifyDataSetChanged()
//
//                })

            }else{
                Toast.makeText(requireContext() , " cant delete this item " , Toast.LENGTH_SHORT).show()

            }


            // favoriteAdapter.setlist(it.draft_orders)
            //CartAdapter.notifyDataSetChanged()

        })

    }

    override fun onPayBtnClicked(totalPrice: Double) {
        var bundle=Bundle()
        bundle.putDouble("SUBTOTAL",totalPrice)
        val cashFragment = CashFragment()
        cashFragment.arguments =bundle
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, cashFragment)?.commit()

    }

//    override fun onIncrementClicked(id: String) {
//
//    }
//
//    override fun onDecrementClicked(id: String) {
////        var count = 0
////                    if (count >= 1) {
////                count--
////                id = count
////                holder.productCount.text = data1[position].line_items?.get(0)?.quantity.toString()
//////                data= CartModel(data1[position])
//////                cartFragment.upgateCart(cartid  ,data)
////                data = CartModel(
////                    data1[position]
////                )
////                cartFragment.upgateCart(cart_id, data)
////            }
//    }
}


//
//private fun swipeToDelete(){
//    ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
//        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
//        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//    ){
//        override fun onMove(
//            recyclerView: RecyclerView,
//            viewHolder: RecyclerView.ViewHolder,
//            target: RecyclerView.ViewHolder
//        ): Boolean {
//            return true
//
//        }
////        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//            val  postion = viewHolder.adapterPosition
//            val item = cartAdapter.data1[postion]
//                   mlist.removeAt(postion)
//            cartAdapter.notifyItemRemoved(postion)
//           deleteitem(cart_id)
//                    Snackbar.make(
//             viewHolder.itemView.findViewById(R.id.cart_constraint),
//            "item ${item}",
//                Snackbar.LENGTH_SHORT
//            ).apply {
//                setAction("Undo"){
/////    mlist.add(item)
//                }
//            }.show()
//        }
//
//    }).attachToRecyclerView(recyclerView)
//}
//}

