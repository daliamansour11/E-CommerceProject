package com.example.e_commerceproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.e_commerceproject.details.view.DetailsFragment
import com.example.e_commerceproject.payment.view.CashFragment

class PaymentFragment : Fragment() {

    lateinit var cashbtn : Button
    lateinit var onlinebtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        cashbtn = view.findViewById(R.id.cashbtn)
        onlinebtn = view.findViewById(R.id.onlinebtn)
        cashbtn.setOnClickListener {
            Toast.makeText(requireContext() , "cash" , Toast.LENGTH_SHORT).show()
            val cashFragment = CashFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, cashFragment)?.commit()

        }

        onlinebtn.setOnClickListener {
            Toast.makeText(requireContext() , "online" , Toast.LENGTH_SHORT).show()
            val onlinePaymentFragment = OnlinePaymentFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, onlinePaymentFragment)?.commit()

        }

    }

}