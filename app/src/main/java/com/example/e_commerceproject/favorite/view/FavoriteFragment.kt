package com.example.e_commerceproject.favorite.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.viewmodel.CartViewModel
import com.example.e_commerceproject.cart.viewmodel.CartViewModelFactory
import com.example.e_commerceproject.favorite.viewmodel.FavoriteViewModel
import com.example.e_commerceproject.favorite.viewmodel.FavoriteViewModelFactory
import com.example.e_commerceproject.network.FavoriteRepository
import com.example.e_commerceproject.network.remotesource.CartRepository
import com.example.e_commerceproject.network.remotesource.RetrofitService

class FavoriteFragment : Fragment() , OnDeletefromFavoriteClikListener{

    private lateinit var favoriteAdapter: FavoriteAdapter
    lateinit var recyclerView: RecyclerView;
    lateinit var viewModel: FavoriteViewModel
   // lateinit var deleteButton: Button
    var productId = ""


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

        recyclerView = view.findViewById(R.id.favorite_recycleview)
       // val layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , true )
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager
        favoriteAdapter = FavoriteAdapter(requireContext() , this)
        recyclerView.adapter = favoriteAdapter
       // deleteButton = view.findViewById(R.id.favorite_delete_from_favorieButton)


        val retrofitService = RetrofitService.getInstance()
        val mainRepository = FavoriteRepository(retrofitService)
        viewModel = ViewModelProvider(this, FavoriteViewModelFactory(mainRepository)).get(FavoriteViewModel::class.java)
        viewModel.getFavoriteProducts()

        viewModel.favoriteProducts.observe(viewLifecycleOwner, {



            if(it.draft_orders.count() <= 2 ){
                Toast.makeText(requireContext() , " moreeee " , Toast.LENGTH_SHORT).show()
            }
            var ar = ArrayList<DraftOrder>()
            ar.add(it.draft_orders[0])
            ar.add(it.draft_orders[1])
            ar.add(it.draft_orders[2])
            ar.add(it.draft_orders[3])
            ar.add(it.draft_orders[4])
            ar.add(it.draft_orders[5])
            ar.add(it.draft_orders[6])
            ar.add(it.draft_orders[7])
            ar.add(it.draft_orders[8])
            ar.add(it.draft_orders[9])
            ar.add(it.draft_orders[10])

            favoriteAdapter.setlist(it.draft_orders)
            favoriteAdapter.notifyDataSetChanged()
            var arfilter = ar.filter {  it.email ==  "jkjkjk@gmail.com" }
            Log.i("TAG", "onViewCreated: ${arfilter} ")
            it.draft_orders.filter { "email" == "jkjkjk@gmail.com" && "note" == "fav" }
            Log.i("TAG", "onViewCreatedjjjjjjjjjjjjjjjjjjjjjjjjjj: ${it.draft_orders.filter { "email" == "reham33@gmail.com" }}")
           // Log.i("TAG", "onViewCreatedjjjjjjjjjjjjjjjjjjjjjjjjjj: ${it.draft_orders }")


        })


    }

    override fun onDeleteFromFavClicked(id: String) {
        Toast.makeText(requireContext() , "kk" , Toast.LENGTH_SHORT).show()

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = FavoriteRepository(retrofitService)
        viewModel = ViewModelProvider(this, FavoriteViewModelFactory(mainRepository)).get(FavoriteViewModel::class.java)
        viewModel.deleteFavoriteProduct(id)

        viewModel.deleteFromFavorite.observe(viewLifecycleOwner, {

            if(it != null){
                Toast.makeText(requireContext() , "deleted sucssefuly" , Toast.LENGTH_SHORT).show()

                viewModel.getFavoriteProducts()

                viewModel.favoriteProducts.observe(viewLifecycleOwner, {



                    it.draft_orders.filter { "email" == "jkjkjk@gmail.com" && "note" == "fav" }
                    Log.i("TAG", "onViewCreatedjjjjjjjjjjjjjjjjjjjjjjjjjj: ${it.draft_orders.filter { "email" == "3bdorafaat@gmail.com" }}")
                    Log.i("TAG", "onViewCreatedjjjjjjjjjjjjjjjjjjjjjjjjjj: ${it.draft_orders }")
                    favoriteAdapter.setlist(it.draft_orders)
                    favoriteAdapter.notifyDataSetChanged()

                })

            }else{
                Toast.makeText(requireContext() , " cant delete this item " , Toast.LENGTH_SHORT).show()

            }


           // favoriteAdapter.setlist(it.draft_orders)
            favoriteAdapter.notifyDataSetChanged()

        })

    }


}