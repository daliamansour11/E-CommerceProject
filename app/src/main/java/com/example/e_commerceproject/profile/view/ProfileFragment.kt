package com.example.e_commerceproject.profile.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.Settings.view.SettingsFragment
import com.example.e_commerceproject.authentication.login.view.LoginFragment
import com.example.e_commerceproject.authentication.register.view.RegisterFragment
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.common.network.NetworkUtils
import com.example.e_commerceproject.favorite.view.FavoriteAdapter
import com.example.e_commerceproject.favorite.view.FavoriteFragment
import com.example.e_commerceproject.favorite.view.OnDeletefromFavoriteClikListener
import com.example.e_commerceproject.favorite.viewmodel.FavoriteViewModel
import com.example.e_commerceproject.favorite.viewmodel.FavoriteViewModelFactory
import com.example.e_commerceproject.home.view.HomeFragment
import com.example.e_commerceproject.network.FavoriteRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.example.e_commerceproject.profile.client.ProfileClient
import com.example.e_commerceproject.profile.model.OrderModel
import com.example.e_commerceproject.profile.model.ProfileRepository
import com.example.e_commerceproject.profile.viewmodel.ProfileViewModel
import com.example.e_commerceproject.profile.viewmodel.ProfileViewModelFactory
import com.google.gson.Gson


class ProfileFragment : Fragment() {
    lateinit var moreorder_btn : Button
    lateinit var moreWishes_btn : Button
    lateinit var login_btn : Button
    lateinit var welcome_text : TextView
    //lateinit var register_btn : Button
    lateinit var profile_back : Button
    lateinit var profile_settings : ImageView
    lateinit var priceTextView: TextView
    lateinit var dateOfOrderTxtView: TextView
    lateinit var viewModel: ProfileViewModel
    lateinit var vmFactory: ProfileViewModelFactory
    private var orderList:ArrayList<OrderModel> = ArrayList()

    private lateinit var favoriteAdapter: WishesAdapter
    lateinit var wishesRecyclerView: RecyclerView;
    lateinit var favoriteviewModel: FavoriteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        //Getting ViewModel Ready
        vmFactory = ProfileViewModelFactory(
            ProfileRepository.getInstance(
                ProfileClient.getInstance(),
                requireContext()
            ))
        viewModel = ViewModelProvider(this, vmFactory).get(ProfileViewModel::class.java)

        if(NetworkUtils.isOnline(requireContext())){
            viewModel.getAllOrders()
        } else {
            Toast.makeText(requireContext(), "Please Check your network connection", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        var profile_frg  = inflater.inflate(R.layout.fragment_profile, container, false)
//        moreWishes_btn = profile_frg.findViewById(R.id.morewish_btn)
        moreorder_btn = profile_frg.findViewById(R.id.moreOrder_btn)
        moreWishes_btn = profile_frg.findViewById(R.id.morewish_btn)
        profile_back = profile_frg.findViewById(R.id.profile_back)
        profile_settings = profile_frg.findViewById(R.id.profile_settings)

        login_btn = profile_frg.findViewById(R.id.login_btn)
        welcome_text = profile_frg.findViewById(R.id.welcome_text)
        //register_btn = profile_frg.findViewById(R.id.register_btn)
        //register_btn = profile_frg.findViewById(R.id.registerBtn)
        priceTextView = profile_frg.findViewById(R.id.order_price1)
        dateOfOrderTxtView = profile_frg.findViewById(R.id.order_date)

        return profile_frg  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs" , Context.MODE_PRIVATE)
        var userEmail : String  = sharedPreferences.getString("EMAIL_LOGIN" , "").toString()
        var userName : String  = sharedPreferences.getString("Name_LOGIN" , "").toString()

        Log.i("TAG", "sharedPreferences: ${userEmail} ")

        if(userEmail != ""){ // data -> logout

            ChangeButtonText("logout")
            welcome_text.text = "Welcome ${userName}"
            // log out btn visible
            // log in  btn unvisible

        }else{ //  no data   -> login
            // log out invisible
            // log in btn visible
            ChangeButtonText("login")
            welcome_text.text = "Welcome "

        }


        moreorder_btn.setEnabled(false)
        viewModel.orderList.observe(viewLifecycleOwner){orders ->
            orderList = orders as ArrayList<OrderModel>
            if(orderList!=null && orderList.size>0){
                moreorder_btn.setEnabled(true)
                priceTextView.text = orderList[0].total_price
                var createdAt = orderList[0].created_at.split("T")[0]
                dateOfOrderTxtView.text = createdAt
            }
        }

        profile_settings.setOnClickListener {

            val settingsFragment = SettingsFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, settingsFragment)?.commit()
            Toast.makeText(context, "go to settings ", Toast.LENGTH_LONG).show()
        }

        moreorder_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val moreOrdersFragment = MoreOrdersFragment()
                val bundle = Bundle()

                bundle.putString("orders", Gson().toJson(orderList))
                moreOrdersFragment.setArguments(bundle)
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, moreOrdersFragment)?.commit()
                Toast.makeText(context, "go to moreOrders ", Toast.LENGTH_LONG).show()

            }
//                val intent = Intent(this,HomeFragment ::class.java)
//                intent.putExtra("key", "Kotlin")
//                startActivity(intent)
//            }

        })

        // wishes
        wishesRecyclerView = view.findViewById(R.id.profile_favorite_recycleview)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        wishesRecyclerView.layoutManager = layoutManager
        //wishesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        favoriteAdapter = WishesAdapter(requireContext())
        wishesRecyclerView.adapter = favoriteAdapter

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = FavoriteRepository(retrofitService)
        favoriteviewModel = ViewModelProvider(this, FavoriteViewModelFactory(mainRepository)).get(FavoriteViewModel::class.java)
        favoriteviewModel.getFavoriteProducts()

        favoriteviewModel.favoriteProducts.observe(viewLifecycleOwner, {

            var ar = ArrayList<DraftOrder>()
            ar.add(it.draft_orders[0])
            ar.add(it.draft_orders[1])
            favoriteAdapter.setlist(ar)
            favoriteAdapter.notifyDataSetChanged()
            it.draft_orders.filter { "email" == "jkjkjk@gmail.com" && "note" == "fav" }
            Log.i("TAG", "onViewCreatedjjjjjjjjjjjjjjjjjjjjjjjjjj: ${it.draft_orders.filter { "email" == "reham33@gmail.com" }}")
            // Log.i("TAG", "onViewCreatedjjjjjjjjjjjjjjjjjjjjjjjjjj: ${it.draft_orders }")


        })


        moreWishes_btn.setOnClickListener{

                val moreWishesFragment = FavoriteFragment()
                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, moreWishesFragment)?.commit()

            }

        login_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                if(userEmail != ""){ // data -> logout
                    val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs" ,
                        Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.apply(){
                        putString("EMAIL_LOGIN" ,  "")
                        putString("PASSWORD_LOGIN" ,  "")
                        putString("Name_LOGIN" ,  "")
                    }.apply()

                    Toast.makeText(requireContext() ,"logout" , Toast.LENGTH_SHORT ).show()

                }else { //  no data   -> login

                    val loginFragment = LoginFragment()
                    fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, loginFragment)?.commit()

                    Toast.makeText(context, "login", Toast.LENGTH_LONG).show()


                }

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




    }
fun ChangeButtonText( text : String){
    login_btn.setText(text)

}


}
/*override fun onMoreOrderClicked() {
Toast.makeText(requireContext(),"gfdj",Toast.LENGTH_LONG).show()    }
}*/

    



