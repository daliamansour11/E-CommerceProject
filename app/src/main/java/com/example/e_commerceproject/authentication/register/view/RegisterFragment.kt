package com.example.e_commerceproject.authentication.register.view

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
import com.example.e_commerceproject.authentication.login.model.Address
import com.example.e_commerceproject.authentication.login.model.MarketingConsent
import com.example.e_commerceproject.authentication.register.model.*
import com.example.e_commerceproject.authentication.register.viewmodel.AuthenticationViewModel
import com.example.e_commerceproject.authentication.register.viewmodel.AuthenticationViewModelFactory
import com.example.e_commerceproject.data.AuthenticationRepository
//import com.example.e_commerceproject.network.
import com.example.e_commerceproject.network.remotesource.RetrofitService


class RegisterFragment : Fragment() {

    lateinit var registernowbtn : Button
    lateinit var firstNameEdittxt :EditText
    lateinit var lastNameEdittxt :EditText
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var confirmPasswordEditText: EditText
    lateinit var phoneEditText: EditText


    lateinit var viewModel: AuthenticationViewModel

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

        val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs" , Context.MODE_PRIVATE)

        Log.i("TAG", "sharedPreferences: ${sharedPreferences.getString("EMAIL_LOGIN" , null)} ")


        registernowbtn = view.findViewById(R.id.registernowbtn)
        firstNameEdittxt = view.findViewById(R.id.register_first_name_edittext)
        lastNameEdittxt = view.findViewById(R.id.register_last_name_edittext)
        emailEditText = view.findViewById(R.id.register_email_edittext)
        passwordEditText = view.findViewById(R.id.register_password_edittext)
        confirmPasswordEditText = view.findViewById(R.id.register_confirmpassword_edittext)
        phoneEditText = view.findViewById(R.id.register_phone_edittext)



       // Log.i("TAG", "onViewCreatedttttttttttttttttttttttttttttttt: ${nameEdittxt.text}")

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = AuthenticationRepository(retrofitService)
        viewModel = ViewModelProvider(this, AuthenticationViewModelFactory(mainRepository)).get(AuthenticationViewModel::class.java)


        var bv = Customer2(
            first_name = "fxewqopokmm" ,
            last_name = "opujhhgcgfdze" ,
            phone = "01247765097",
            tags = "bvbvcxzzbnmmyhg",
            email = "mkloouhwqawerqqe@gmail.com"
        )

        var f: String = firstNameEdittxt.text.toString()
        var l: String = firstNameEdittxt.text.toString()
        var p: String = firstNameEdittxt.text.toString()
        var e: String = firstNameEdittxt.text.toString()
        var t: String = firstNameEdittxt.text.toString()


        var vb = Customer2(
            first_name = "${f}" ,
            last_name = "${l}"  ,
            phone = "${p}",
            tags = "${p}",
            email = "${e}"
        )

        var  customerdata = CustomerModel(bv)

        registernowbtn.setOnClickListener {

            //Log.i("TAG", "onViewCreatedttttttttttttttttttttttttttttttt: ${nameEdittxt.text}")

            viewModel.postRegisterCustomer(customerdata) // postRegisterCustomer
            viewModel.customerdata.observe(viewLifecycleOwner, {

                //Log.i("TAG", "onViewCreated:rrrrrrrrrrrr ${it}")
                Log.i("TAG", "onViewCreated: ${it.customer.phone}")
            })

            Toast.makeText(requireContext() , "registernow" , Toast.LENGTH_SHORT).show()

        }

    }

}