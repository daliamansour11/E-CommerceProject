package com.example.e_commerceproject.payment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.e_commerceproject.R


class CashFragment : Fragment() {

    lateinit var placeorderbtn : Button
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
        var cashFrag=  inflater.inflate(R.layout.fragment_cash, container, false)
        placeorderbtn = cashFrag.findViewById(R.id.placeorder_btn)
        return cashFrag
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        placeorderbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                Toast.makeText(context, "place your Order ", Toast.LENGTH_LONG).show()

            }
//                val intent = Intent(this,HomeFragment ::class.java)
//                intent.putExtra("key", "Kotlin")
//                startActivity(intent)
//            }

        })

    }
}