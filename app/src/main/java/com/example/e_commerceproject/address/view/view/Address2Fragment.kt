package com.example.e_commerceproject.address.view.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.R
import com.example.e_commerceproject.Settings.view.SettingsFragment
import com.example.e_commerceproject.address.view.viewModel.AddressViewModel
import com.example.e_commerceproject.address.view.viewModel.AddressViewModelFactory
import com.example.e_commerceproject.authentication.register.model.CustomerAddress
import com.example.e_commerceproject.network.remotesource.AdressRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService

class Address2Fragment : Fragment() {
    lateinit var back : Button
    lateinit var addersstxt :TextView
    lateinit var counterytxt :TextView
    lateinit var phonetxt :TextView
    var customerId: String = "5775923609739"//207119551   //5770511351947
    lateinit var viewModel: AddressViewModel


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
        counterytxt = address2frg.findViewById(R.id.countery_txt)
        addersstxt = address2frg.findViewById(R.id.adderss_txt)
        phonetxt = address2frg.findViewById(R.id.phone_txt)
        return address2frg
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = AdressRepository(retrofitService)

        viewModel = ViewModelProvider(this, AddressViewModelFactory(mainRepository)).get(
            AddressViewModel::class.java)
        viewModel.getAddress(customerId)
        viewModel.getCustomerAddresses.observe(viewLifecycleOwner, {
            Log.d("TAG", "inside addresss2fragment")
            counterytxt.text = it.addresses[0].country_name
            addersstxt.text = it.addresses[0].address1
            phonetxt.text = it.addresses[1].phone
            Log.i("TAG", "onViewCreated:rrrrrrTTTTTTTTTrrrrrr ${it}")
        })
        back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val addressFragment = AddressFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, addressFragment)?.commit()
            }
        })

    }
}