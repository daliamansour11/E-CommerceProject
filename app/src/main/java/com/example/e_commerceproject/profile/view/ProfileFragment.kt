package com.example.e_commerceproject.profile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.e_commerceproject.R
import com.example.e_commerceproject.Settings.view.SettingsFragment
import com.example.e_commerceproject.athentication.login.view.LoginFragment
import com.example.e_commerceproject.athentication.register.view.RegisterFragment
import com.example.e_commerceproject.home.view.HomeFragment


class ProfileFragment : Fragment() {
    lateinit var moreorder_btn : Button
    lateinit var moreWishes_btn : Button
    lateinit var login_btn : Button
    lateinit var register_btn : Button
    lateinit var profile_back : Button
    lateinit var profile_settings : ImageView
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
        var profile_frg  = inflater.inflate(R.layout.fragment_profile, container, false)
        moreWishes_btn = profile_frg.findViewById(R.id.morewish_btn)
        moreorder_btn = profile_frg.findViewById(R.id.moreOrder_btn)
        profile_back = profile_frg.findViewById(R.id.profile_back)
        profile_settings = profile_frg.findViewById(R.id.profile_settings)
        login_btn = profile_frg.findViewById(R.id.login_btn)
        register_btn = profile_frg.findViewById(R.id.register_btn)
        return profile_frg  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profile_settings.setOnClickListener {

            val settingsFragment = SettingsFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, settingsFragment)?.commit()
            Toast.makeText(context, "go to settings ", Toast.LENGTH_LONG).show()

        }

        moreorder_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val moreOrdersFragment = moreOrdersFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, moreOrdersFragment)?.commit()
                Toast.makeText(context, "go to moreOrders ", Toast.LENGTH_LONG).show()

            }
//                val intent = Intent(this,HomeFragment ::class.java)
//                intent.putExtra("key", "Kotlin")
//                startActivity(intent)
//            }

        })

        moreWishes_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val moreWishesFragment = MoreWishesFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, moreWishesFragment)?.commit()
                Toast.makeText(context, "go to morewishes ", Toast.LENGTH_LONG).show()

            }
//                val intent = Intent(this,HomeFragment ::class.java)
//                intent.putExtra("key", "Kotlin")
//                startActivity(intent)
//            }

        })
        login_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val loginFragment = LoginFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, loginFragment)?.commit()

                Toast.makeText(context, "login", Toast.LENGTH_LONG).show()

            }
//                val intent = Intent(this,HomeFragment ::class.java)
//                intent.putExtra("key", "Kotlin")
//                startActivity(intent)
//            }

        })
        register_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val registerFragment = RegisterFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, registerFragment)?.commit()

                Toast.makeText(context, "register", Toast.LENGTH_LONG).show()

            }
//                val intent = Intent(this,HomeFragment ::class.java)
//                intent.putExtra("key", "Kotlin")
//                startActivity(intent)
//            }

        })
        profile_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val loginFragment = HomeFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, loginFragment)?.commit()

                Toast.makeText(context, "login", Toast.LENGTH_LONG).show()

            }
//                val intent = Intent(this,HomeFragment ::class.java)
//                intent.putExtra("key", "Kotlin")
//                startActivity(intent)
//            }

        })

    }}
/*override fun onMoreOrderClicked() {
Toast.makeText(requireContext(),"gfdj",Toast.LENGTH_LONG).show()    }
}*/