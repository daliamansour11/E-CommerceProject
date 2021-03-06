package com.example.e_commerceproject.address.view.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.R
import com.example.e_commerceproject.Settings.view.SettingsFragment
import com.example.e_commerceproject.address.view.viewModel.AddressViewModel
import com.example.e_commerceproject.address.view.viewModel.AddressViewModelFactory
import com.example.e_commerceproject.authentication.register.model.Customer2
import com.example.e_commerceproject.authentication.register.model.CustomerAddress
import com.example.e_commerceproject.authentication.register.model.CustomerModel
import com.example.e_commerceproject.authentication.register.model.PostAddress
import com.example.e_commerceproject.authentication.register.viewmodel.AuthenticationViewModel
import com.example.e_commerceproject.authentication.register.viewmodel.AuthenticationViewModelFactory
import com.example.e_commerceproject.data.AuthenticationRepository
import com.example.e_commerceproject.network.remotesource.AdressRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService

class AddressFragment : Fragment() {
    lateinit var addbtn: Button
    lateinit var back: Button
    lateinit var counteryEdittxt: EditText
    lateinit var cityEdittxt: EditText
    lateinit var AdderssEditText: EditText
    lateinit var phoneEditText: EditText
    lateinit var ZipcodeEditText: EditText
    lateinit var viewModel: AddressViewModel
    //var customerId: String = "5775923609739"//5770511351947
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
        var address1_frg = inflater.inflate(R.layout.fragment_address, container, false)

        addbtn = address1_frg.findViewById(R.id.AddAddress_btn)
        back = address1_frg.findViewById(R.id.addressback_btn)
        cityEdittxt = address1_frg.findViewById(R.id.city)
        counteryEdittxt = address1_frg.findViewById(R.id.country)
        AdderssEditText = address1_frg.findViewById(R.id.address)
        phoneEditText = address1_frg.findViewById(R.id.phone)
//        ZipcodeEditText =  address1_frg.findViewById(R.id.zip_code)

        return address1_frg
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs", Context.MODE_PRIVATE)
        var customerId: String = sharedPreferences.getString("CUSTOMER_ID", "").toString()


        val retrofitService = RetrofitService.getInstance()
        val mainRepository = AdressRepository(retrofitService)
        viewModel = ViewModelProvider(this, AddressViewModelFactory(mainRepository)).get(
            AddressViewModel::class.java
        )


        addbtn.setOnClickListener({

            //var customer  = Customer2()
            var myAddressDetails = CustomerAddress(
                address1 = "${AdderssEditText.text}",
                city = "${cityEdittxt.text}",
                country = "${counteryEdittxt.text}",
                phone = "${phoneEditText.text}",
                id = 6983484997771
            )
            var postAddress = PostAddress(myAddressDetails)
            //    customer.addresses = listOf(myAddressDetails)
            // var custom_Address = CustomerModel(customer)
            viewModel.pushPostAddress(customerId, postAddress)
            viewModel.postCustomerAddress.observe(viewLifecycleOwner, {
                Log.i("TAGGGGGGGGGGGGGGGGGGGGGGGGGGG", "onViewCreated: ${it}")
            })
            val address2Fragment = Address2Fragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, address2Fragment)?.commit()

        })
        back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val settingsFragment = SettingsFragment()
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentContainerView, settingsFragment)?.commit()
            }

        })

    }
}