package com.example.e_commerceproject.athentication.register.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.e_commerceproject.R


class RegisterFragment : Fragment() {

    lateinit var registernowbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registernowbtn = view.findViewById(R.id.registernowbtn)


        registernowbtn.setOnClickListener {
            Toast.makeText(requireContext() , "registernow" , Toast.LENGTH_SHORT).show()

        }



    }

}