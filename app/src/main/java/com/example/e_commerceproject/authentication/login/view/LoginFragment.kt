package com.example.e_commerceproject.authentication.login.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceproject.R
import com.example.e_commerceproject.authentication.login.viewmodel.LoginViewModel
import com.example.e_commerceproject.authentication.login.viewmodel.LoginViewModelFactory
import com.example.e_commerceproject.authentication.register.view.RegisterFragment
import com.example.e_commerceproject.data.AuthenticationRepository
import com.example.e_commerceproject.home.view.HomeFragment
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.example.e_commerceproject.profile.view.ProfileFragment


class LoginFragment : Fragment() {

    lateinit var loginbtn : Button
    lateinit var registerbtn : Button
    lateinit var email:EditText
    lateinit var password :EditText
    lateinit var viewModel: LoginViewModel
    lateinit var backButton : ImageView

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

        val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs" ,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()


        loginbtn = view.findViewById(R.id.loginbtn)
        registerbtn = view.findViewById(R.id.registerbtn)
        email = view.findViewById(R.id.login_email_editText)
        password = view.findViewById(R.id.login_password_editText)
        backButton = view.findViewById(R.id.loginArrowBack)


        val retrofitService = RetrofitService.getInstance()
        val mainRepository = AuthenticationRepository(retrofitService)
        viewModel = ViewModelProvider(this, LoginViewModelFactory(mainRepository)).get(LoginViewModel::class.java)


        loginbtn.setOnClickListener {

            //viewModel.getCustomerById("jkjkjk@gmail.com")
            // mnmnmnmnmn
            viewModel.getCustomerById("${email.text}")
            viewModel.customer.observe(viewLifecycleOwner, {
                if(it.customers[0].email == "${email.text}" && it.customers[0].tags == "${password.text}" ){
                    Toast.makeText(requireContext() , "loged in successfully" , Toast.LENGTH_SHORT).show()

                    editor.apply(){
                        putString("EMAIL_LOGIN" ,  "${email.text}")
                        putString("PASSWORD_LOGIN" ,  "${password.text}")
                        putString("Name_LOGIN" ,  "${it.customers[0].first_name}")
                        putString("CUSTOMER_ID" ,  "${it.customers[0].id}")
                    }.apply()
                    val homeFragment = HomeFragment()
                    fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, homeFragment)?.commit()

                    // saveDataInSharedPrefrence()
                }else if(it.customers[0].email == "${email.text}" && it.customers[0].tags != "${password.text}" ){
                    Toast.makeText(requireContext() , "invalid password" , Toast.LENGTH_SHORT).show()
                }

                Log.i("TAG", "onViewCreatedjjjjjjjjjjjjjjj: ${it.customers}")
            })
        }

        registerbtn.setOnClickListener {

            val registerFragment = RegisterFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, registerFragment)?.commit()

        }

        backButton.setOnClickListener {
            val profileFragment = ProfileFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, profileFragment)?.commit()
        }

    }

    private fun saveDataInSharedPrefrence() {
        val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs" ,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet("jhj" , setOf("jh" , "ghg"))
       // val draftOrderIdSet  = mutableSetOf("")
        val draftOrderIdSet: Set<String> = HashSet()


        //val mySet: Set<String> = HashSet<String>(wordsQueue)
       // editor.putStringSet("Words", draftOrderIdSet).apply()
        editor.apply(){

            putString("EMAIL_LOGIN" ,  "${email.text}")
            putString("PASSWORD_LOGIN" ,  "${password.text}")
        }.apply()

        Toast.makeText(requireContext() , "data saved" , Toast.LENGTH_SHORT).show()

    }

}

