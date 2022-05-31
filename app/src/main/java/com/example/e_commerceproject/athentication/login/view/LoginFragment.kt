package com.example.e_commerceproject.athentication.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.e_commerceproject.R
import com.example.e_commerceproject.details.view.DetailsFragment


class LoginFragment : Fragment() {

    lateinit var loginbtn : Button
    lateinit var registerbtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginbtn = view.findViewById(R.id.loginbtn)
        registerbtn = view.findViewById(R.id.registerbtn)

        loginbtn.setOnClickListener {
            Toast.makeText(requireContext() , "login" , Toast.LENGTH_SHORT).show()
        }

        registerbtn.setOnClickListener {

            Toast.makeText(requireContext() , "register" , Toast.LENGTH_SHORT).show()
            val detailsfragment = DetailsFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, detailsfragment)?.commit()

        }

    }

}