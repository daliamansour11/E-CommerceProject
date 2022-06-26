package com.example.e_commerceproject.profile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.e_commerceproject.R



class MoreWishesFragment : Fragment() {

    lateinit var morewish_back  : Button
    // Navigation.findNavController(requireView()).navigate(R.id.action_favoriteFragment2_to_favoriteDetailsFragment)
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
        var morewish_frag =  inflater.inflate(R.layout.fragment_more_wishes, container, false)
        morewish_back = morewish_frag .findViewById(R.id.morewish_back)
        return  morewish_frag

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        morewish_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var bundle = Bundle()
                val df = ProfileFragment()
                df.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, df)?.commit()
                // Navigation.findNavController(requireView()).navigate(R.id.action_favoriteFragment2_to_favoriteDetailsFragment)
            }


        })
    }}