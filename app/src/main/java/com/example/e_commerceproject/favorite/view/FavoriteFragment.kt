package com.example.e_commerceproject.favorite.view

import android.content.ContentValues
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
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.authentication.login.view.LoginFragment
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.viewmodel.CartViewModel
import com.example.e_commerceproject.cart.viewmodel.CartViewModelFactory
import com.example.e_commerceproject.currencyConverter.viewModel.ConverterViewModel
import com.example.e_commerceproject.currencyConverter.viewModel.ConverterViewModelFactory
import com.example.e_commerceproject.favorite.viewmodel.FavoriteViewModel
import com.example.e_commerceproject.favorite.viewmodel.FavoriteViewModelFactory
import com.example.e_commerceproject.home.view.HomeFragment
import com.example.e_commerceproject.network.ConverterRepository
import com.example.e_commerceproject.network.FavoriteRepository
import com.example.e_commerceproject.network.remotesource.CartRepository
import com.example.e_commerceproject.network.remotesource.ConverterApiService
import com.example.e_commerceproject.network.remotesource.RetrofitService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FavoriteFragment : Fragment() , OnDeletefromFavoriteClikListener{

    private lateinit var favoriteAdapter: FavoriteAdapter
    lateinit var recyclerView: RecyclerView;
    lateinit var viewModel: FavoriteViewModel
    lateinit var backButton : ImageView
    var productId = ""
    lateinit var CviewModel: ConverterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs", Context.MODE_PRIVATE)
        var userEmail: String = sharedPreferences.getString("EMAIL_LOGIN", "").toString()
        var currencyTtpe: String = sharedPreferences.getString("CURRENCY_TYPE_RESULT", "").toString()


        var from = "USD"
        var to = "EGP"
        if(currencyTtpe == "EGP"){
            var to = "EGP"
            from = "EGP"
        }else if (currencyTtpe == "USD"){
            var to = "EGP"
            from = "USD"
        }

        val retrofitServicee = ConverterApiService.getInstance()
        val mainRepositoryy = ConverterRepository(retrofitServicee)
        CviewModel = ViewModelProvider(this, ConverterViewModelFactory(mainRepositoryy)).get(ConverterViewModel::class.java)
/*
        CviewModel.getcontvertedResponse("6gojh955Of5UkFW6fPN3W2nq1Isj5BqC" ,to , "1" , from)
        CviewModel._Convert_Response.observe(viewLifecycleOwner) { respo ->

            if(respo!=null){
                Log.i(ContentValues.TAG, "onChangedDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD: ${respo.result}")
                System.out.println("Re" + respo.result)

                val sharedPreferences : SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs" ,Context.MODE_PRIVATE)
                val editorr = sharedPreferences.edit()
                editorr.apply(){
                    putString("CURRENCY_CONVERTER_RESULT" ,  "${respo.result}")
                }.apply()
            }


        }
*/

        backButton = view.findViewById(R.id.favoriteArrowBack)

        recyclerView = view.findViewById(R.id.favorite_recycleview)
       // val layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , true )
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager
        favoriteAdapter = FavoriteAdapter(requireContext() , this)
        recyclerView.adapter = favoriteAdapter

//        var currenc = "EGP"
//        var currencyTtp: String = sharedPreferences.getString("CURRENCY_TYPE_RESULT", "").toString()
//        var converterRespons: String = sharedPreferences.getString("CURRENCY_CONVERTER_RESULT", "").toString()
//        if( currencyTtp == "EGP"){
//            currenc = "EGP"
//        }else if ( currencyTtp == "USD"){
//            currenc = "$"
//        }
//        var rr = converterRespons.toDouble()
//        Log.i("TAG", "onBindViewHolder: converterResponse ${rr}")
//

        var currencyTtp: String = sharedPreferences.getString("CURRENCY_TYPE_RESULT", "").toString()
        var converterRespons: String = sharedPreferences.getString("CURRENCY_CONVERTER_RESULT", "").toString()
        favoriteAdapter.setCurrency(currencyTtp , converterRespons )


        val retrofitService = RetrofitService.getInstance()
        val mainRepository = FavoriteRepository(retrofitService)
        viewModel = ViewModelProvider(this, FavoriteViewModelFactory(mainRepository)).get(FavoriteViewModel::class.java)

        if (userEmail == null || userEmail == "") {
            // navigate to login screen
            Toast.makeText(requireContext(), "you must login or register first", Toast.LENGTH_SHORT).show()

            val loginFragment = LoginFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView, loginFragment)?.commit()

        }else{
            viewModel.getFavoriteProducts()

            viewModel.favoriteProducts.observe(viewLifecycleOwner, {

                favoriteAdapter.setlist(it.draft_orders.filter { it.email ==  userEmail && it.note == "fav" })
                favoriteAdapter.notifyDataSetChanged()
             //   Log.i("TAG", "onViewCreatedjjjjjjjjjjjjjjjjjjjjjjjjjj: ${it.draft_orders.filter { it.email ==  "jkjkjk@gmail.com" && it.note == "fav" }.count()}  ")

            })
        }

        backButton.setOnClickListener {
            val homeFragment = HomeFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, homeFragment)?.commit()
        }

    }

    fun showAlertDialog( id : String){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete")
            .setMessage("Are you sure you want to delete this item ")
            .setNeutralButton(""){dialog  , which ->

            }
            .setNegativeButton("No"){dialog  , which ->

            }
            .setPositiveButton("Yes"){dialog  , which ->
                deleteFavoriteProduct(id)
            }.show()
    }

    override fun onDeleteFromFavClicked(id: String) {

        showAlertDialog(id)

    }

    fun deleteFavoriteProduct(id:String){


        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("loginsharedprefs", Context.MODE_PRIVATE)
        var userEmail: String = sharedPreferences.getString("EMAIL_LOGIN", "").toString()

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = FavoriteRepository(retrofitService)
        viewModel = ViewModelProvider(this, FavoriteViewModelFactory(mainRepository)).get(FavoriteViewModel::class.java)
        viewModel.deleteFavoriteProduct(id)

        viewModel.deleteFromFavorite.observe(viewLifecycleOwner, {

            if(it != null){
               // Toast.makeText(requireContext() , "deleted sucssefuly" , Toast.LENGTH_SHORT).show()

                viewModel.getFavoriteProducts()
                viewModel.favoriteProducts.observe(viewLifecycleOwner, {

                    favoriteAdapter.setlist(it.draft_orders.filter { it.email ==  userEmail && it.note == "fav" })
                    favoriteAdapter.notifyDataSetChanged()

                })

            }else{
              //  Toast.makeText(requireContext() , " cant delete this item " , Toast.LENGTH_SHORT).show()
            }

            favoriteAdapter.notifyDataSetChanged()

        })
    }

}