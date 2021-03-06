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
import com.example.e_commerceproject.authentication.login.view.LoginFragment
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.model.LineItem
import com.example.e_commerceproject.cart.viewmodel.CartViewModel
import com.example.e_commerceproject.cart.viewmodel.CartViewModelFactory
import com.example.e_commerceproject.category.view.CategoryFragment
import com.example.e_commerceproject.currencyConverter.view.CURRUNEY_TYPE
import com.example.e_commerceproject.currencyConverter.view.SHARD_NAME
import com.example.e_commerceproject.currencyConverter.viewModel.ConverterViewModel
import com.example.e_commerceproject.currencyConverter.viewModel.ConverterViewModelFactory
import com.example.e_commerceproject.favorite.viewmodel.FavoriteViewModel
import com.example.e_commerceproject.favorite.viewmodel.FavoriteViewModelFactory
import com.example.e_commerceproject.network.ConverterRepository
import com.example.e_commerceproject.network.FavoriteRepository
import com.example.e_commerceproject.network.remotesource.CartRepository
import com.example.e_commerceproject.network.remotesource.ConverterApiService
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.example.e_commerceproject.payment.model.AddedOrderModel
import com.example.e_commerceproject.payment.view.CashFragment
import com.example.e_commerceproject.payment.view.PaymentFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import java.util.stream.Collectors
import kotlin.streams.toList

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


        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs", Context.MODE_PRIVATE)
        var currencyTtpe: String = sharedPreferences.getString("CURRENCY_TYPE_RESULT", "").toString()


        var from = "USD"
        var to = "EGP"
        if(currencyTtpe == "EGP"){
            var to = "EGP"
            from = "EGP"
        }else if (currencyTtpe == "USD"){
            var to = "EGP"
            from = "USD"
        }

        val retrofitServicee = ConverterApiService.getInstance()
        val mainRepositoryy = ConverterRepository(retrofitServicee)
        CviewModel = ViewModelProvider(this, ConverterViewModelFactory(mainRepositoryy)).get(ConverterViewModel::class.java)
/*
        CviewModel.getcontvertedResponse("6gojh955Of5UkFW6fPN3W2nq1Isj5BqC" ,to , "1" , from)
        CviewModel._Convert_Response.observe(viewLifecycleOwner) { respo ->

            if(respo!=null){
                Log.i(ContentValues.TAG, "onChangedDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD: ${respo.result}")
                System.out.println("Re" + respo.result)

                val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs" ,Context.MODE_PRIVATE)
                val editorr = sharedPreferences.edit()
                editorr.apply(){
                    putString("CURRENCY_CONVERTER_RESULT" ,  "${respo.result}")
                }.apply()
            }


        }

*/

        // Inflate the layout for this fragment
        var cart_frag = inflater.inflate(R.layout.fragment_cart, container, false)

        total_price = cart_frag.findViewById(R.id.textView3)
        return cart_frag
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs", Context.MODE_PRIVATE)
        var userEmail: String = sharedPreferences.getString("EMAIL_LOGIN", "").toString()

        var price = 0.0

        payButtonCart = view.findViewById(R.id.payButtonCart)

        recyclerView = view.findViewById(R.id.cartRecyclerView)
        val cartLinearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
        cartLinearLayoutManager.orientation = RecyclerView.VERTICAL
        cartAdapter = CartAdapter(requireContext(), this , this )
        //swipeToDelete()

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = CartRepository(retrofitService)
        recyclerView.layoutManager = cartLinearLayoutManager
        recyclerView.adapter = cartAdapter
        viewModel = ViewModelProvider(this, CartViewModelFactory(mainRepository)).get(CartViewModel::class.java)
        if (userEmail == null || userEmail == "") {
            // navigate to login screen
            Toast.makeText(requireContext(), "you must login or register first", Toast.LENGTH_SHORT).show()

            val loginFragment = LoginFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, loginFragment)?.commit()

        }else{

            viewModel.getCart()
            viewModel.cart_Response.observe(viewLifecycleOwner, {

                Log.d("TAG", "inside cartfragment")
                Log.i("TAG", "onViewCreated:rrrrrrTTTTTTTTTrrrrrr ${it}")
                for (i in 0..it.draft_orders.size - 1) {
                    Log.i("UserEMAIL", "onViewCreated: email======================" + userEmail)

                    if (it.draft_orders.get(i).email == userEmail && it.draft_orders.get(i).note == "cart" ) {  //"reham33@gmail.com"

                       price = it.draft_orders[i].total_price?.toDouble() ?: 0.0
                    }

                }

                cartAdapter.setlist(it.draft_orders.filter { it.email ==  userEmail && it.note == "cart" })
                total_price.text = "${price.toString()} EGP"
                cartAdapter.notifyDataSetChanged()
            })
        }


        var currencyTtp: String = sharedPreferences.getString("CURRENCY_TYPE_RESULT", "").toString()
        var converterRespons: String = sharedPreferences.getString("CURRENCY_CONVERTER_RESULT", "").toString()
        cartAdapter.setCurrency(currencyTtp , converterRespons )

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
            val sharedPreferences: SharedPreferences =
                requireContext().getSharedPreferences("loginsharedprefs", Context.MODE_PRIVATE)
            var userEmail: String = sharedPreferences.getString("EMAIL_LOGIN", "").toString()
            val paymentFragment = PaymentFragment()
            val lineItems : MutableList<LineItem> = mutableListOf()
            val draftOrders: List<DraftOrder> = cartAdapter.data1
            for (draftOrder in draftOrders){
                lineItems.addAll(draftOrder.line_items!!)
            }
            var addedOrderModel = AddedOrderModel(userEmail,lineItems)
            bundle.putString("addedOrderModel", Gson().toJson(addedOrderModel))
            //paymentFragment.setArguments(bundle)

            Log.i("TAG", "onViewCreated priceeeeeeeeeeeeeeeeeeeeeeeeeeee: ${price}")
            bundle.putDouble("TOTAL_PRICE" , price)
            paymentFragment.arguments = bundle

            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, paymentFragment)?.commit()


        }
    }

    fun upgateCart(cartid: String, data: CartModel) {
        viewModel.updateCart(cartid, data)
    }

//    fun deleteitem(cartid: String) {
//        viewModel.deleteCart(cart_id)
//    }

    fun showAlertDialog( id : String){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete")
            .setMessage("Are you sure you want to delete this item ")
            .setNeutralButton(""){dialog  , which ->

            }
            .setNegativeButton("No"){dialog  , which ->

            }
            .setPositiveButton("Yes"){dialog  , which ->
                deleteFavoriteProduct(id)
            }.show()
    }

    override fun onDeleteFromFavClicked(id: String) {

        showAlertDialog(id)
    }

    fun deleteFavoriteProduct(id:String){

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = CartRepository(retrofitService)
        viewModel = ViewModelProvider(this, CartViewModelFactory(mainRepository)).get(CartViewModel::class.java)
        viewModel.deleteCartProduct(id)

        viewModel.deleteCart_Res.observe(viewLifecycleOwner, {

            if(it != null){
                Toast.makeText(requireContext() , "deleted sucssefuly" , Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(requireContext() , " cant delete this item " , Toast.LENGTH_SHORT).show()

            }

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

