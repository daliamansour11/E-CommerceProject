package com.example.e_commerceproject.payment.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.e_commerceproject.R
import com.example.e_commerceproject.cart.view.CartFragment
import com.example.e_commerceproject.payment.model.AddedOrderModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PaymentFragment : Fragment() {

    lateinit var cashbtn : Button
    lateinit var onlinebtn : Button
    lateinit var back:ImageView
    lateinit var addedOrderModel: AddedOrderModel
    var totalPrice = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {bundle->
            class Token : TypeToken<AddedOrderModel>()
            addedOrderModel = Gson().fromJson(bundle.getString("addedOrderModel"), Token().type)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var args = this.arguments
        if(args == null){
        }else{
            totalPrice =  args?.get("TOTAL_PRICE") as Double
            Log.i("TAG", "onViewCreatedmmmmmmmmmmmmmmmmmmmmmmm: ${totalPrice}")
        }

        cashbtn = view.findViewById(R.id.cashbtn)
        onlinebtn = view.findViewById(R.id.onlinebtn)
        back =view.findViewById(R.id.paymentoption_backarrow)
        cashbtn.setOnClickListener {
            val cashFragment = CashFragment()
            var bundle = Bundle()
            bundle.putString("addedOrderModel", Gson().toJson(addedOrderModel))
            bundle.putDouble("TOTAL_PRICE" , totalPrice)

            cashFragment.arguments = bundle

            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, cashFragment)?.commit()

        }

        onlinebtn.setOnClickListener {
            val onlinePaymentFragment = OnlinePaymentFragment()
            var bundle = Bundle()
            bundle.putString("addedOrderModel", Gson().toJson(addedOrderModel))
            bundle.putDouble("TOTAL_PRICE" , totalPrice)

            onlinePaymentFragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, onlinePaymentFragment)?.commit()

        }
        back.setOnClickListener {
            val cartFragment = CartFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, cartFragment)?.commit()

        }

    }

}