package com.example.e_commerceproject.address.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.e_commerceproject.R

class Address2Fragment : Fragment() {
    lateinit var back : Button

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

        var address2frg = inflater.inflate(R.layout.fragment_address2, container, false)

        back = address2frg.findViewById(R.id.back_btn)

        return address2frg
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                Toast.makeText(context, " your address info", Toast.LENGTH_LONG).show()

            }


        })

    }
}