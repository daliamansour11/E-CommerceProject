package com.example.e_commerceproject.details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.e_commerceproject.R


class DetailsFragment : Fragment() {

    lateinit var detailsaddtofavorieButton: Button
    lateinit var addtocartbtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsaddtofavorieButton = view.findViewById(R.id.detailsaddtofavorieButton)
        detailsaddtofavorieButton.setOnClickListener {
            Toast.makeText(requireContext() , "iu" , Toast.LENGTH_SHORT).show()
        }

        addtocartbtn = view.findViewById(R.id.addtocartbtn)
        addtocartbtn.setOnClickListener {
            Toast.makeText(requireContext() , "po" , Toast.LENGTH_SHORT).show()
        }

    }

}