package com.example.e_commerceproject.address.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.e_commerceproject.R

class AddressFragment : Fragment() {
    lateinit var addbtn :Button
    lateinit var back :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var address1_frg =  inflater.inflate(R.layout.fragment_address, container, false)

        addbtn = address1_frg.findViewById(R.id.AddAddress_btn)
        back = address1_frg.findViewById(R.id.addressback_btn)

    return  address1_frg
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addbtn .setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

               // val address2Fragment = Address2Fragment()
              // fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, address2Fragment)?.commit()
                Toast.makeText(context, "address added" , Toast.LENGTH_LONG).show()

            }

        })
        back .setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                Toast.makeText(context, "go back to setting ", Toast.LENGTH_LONG).show()

            }

        })

    }
}
