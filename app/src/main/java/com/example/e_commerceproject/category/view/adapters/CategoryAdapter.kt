package com.example.e_commerceproject.category.view.adapters

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceproject.R
import com.example.e_commerceproject.cart.model.CartListModel
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.category.model.CategoryModel
import com.example.e_commerceproject.category.model.Product
import com.example.e_commerceproject.category.view.OnFavoriteButtonClickListener
import com.example.e_commerceproject.category.view.OnProductClickInterface

class CategoryAdapter (var context: Context , val onProductClickInterface: OnProductClickInterface , val onFavoriteButtonClickListener: OnFavoriteButtonClickListener) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var dataList : List<CategoryModel> = ArrayList()
    var draftList : MutableSet<String> = mutableSetOf("")
    var currencyTtpe = ""
    var converterResponse = ""


    private var data:List<Product> = ArrayList()

    fun setlist(dataList: List<Product>){
        this.data = dataList
    }

    fun setDraftlist(draftList: MutableSet<String>){
        this.draftList = draftList
    }

    fun setCurrency(type : String , response : String){
        this.currencyTtpe = type
        this.converterResponse = response
    }

    internal fun setDataList(dataList: List<CategoryModel>) {
        this.dataList = dataList
    }

    // Provide a direct reference to each of the views with data items

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var categoryaddfavorieimageButton : Button
        var categoryReomveFromFavorieButton : Button

        var productitemview : ConstraintLayout


        init {
            image = itemView.findViewById(R.id.categoriesimageView)
            title = itemView.findViewById(R.id.categoriestextView)
            categoryaddfavorieimageButton = itemView.findViewById(R.id.category_add_to_favorieButton)
            categoryReomveFromFavorieButton = itemView.findViewById(R.id.category_remove_favorieButton)

            productitemview = itemView.findViewById(R.id.productitemview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.categorycustomrow, parent, false)
        return ViewHolder(view)
    }

     override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {

        var data = data[position]
         var currency = "EGP"

         if( currencyTtpe == "EGP"){
             currency = "EGP"
         }else if ( currencyTtpe == "USD"){
             currency = "$"
         }

         var r = converterResponse.toDouble()
         Log.i("TAG", "onBindViewHolder: converterResponse ${r}")

         if(currency == "$"){
             holder.title.text = ("${((data.variants[0].price).toDouble() / r).toInt().plus(1)}.00 ${currency}")
         }else{
             holder.title.text = ("${((data.variants[0].price).toDouble() / r).toInt()}.00 ${currency}")
         }
         // holder.title.text = ("${(data.variants[0].price)}.00 EGP")

         Glide.with(context).load(data.image.src)
             .placeholder(R.drawable.ic_launcher_background)
             .error(R.drawable.ic_launcher_foreground)
             .into(holder.image)

         holder.productitemview.setOnClickListener {
            onProductClickInterface.onProductClick(data.id)

        }
         Log.i("TAG", "onBindViewHolderrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr: ${draftList}")


             if (data.id.toString() in draftList){
                 Log.i("TAG", "onBindViewHolderrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr: ${data.id.toString()}  ")
                 holder.categoryaddfavorieimageButton.visibility = View.GONE
                 holder.categoryReomveFromFavorieButton.visibility = View.VISIBLE

             }else{
                 holder.categoryaddfavorieimageButton.visibility = View.VISIBLE
                 holder.categoryReomveFromFavorieButton.visibility = View.GONE

             }


          // holder.categoryaddfavorieimageButton.visibility = View.VISIBLE
          // holder.categoryReomveFromFavorieButton.visibility = View.GONE

         holder.categoryaddfavorieimageButton.setOnClickListener {
             holder.categoryaddfavorieimageButton.visibility = View.GONE
             holder.categoryReomveFromFavorieButton.visibility = View.VISIBLE

             onFavoriteButtonClickListener.OnFavoriteButtonClickListener(data)
         }

         holder.categoryReomveFromFavorieButton.setOnClickListener {
             holder.categoryReomveFromFavorieButton.visibility = View.GONE
             holder.categoryaddfavorieimageButton.visibility = View.VISIBLE

             onFavoriteButtonClickListener.OnRemoveFromFavoriteButtonClickListener(data)
         }
//


    }

//    fun setList(favorit: ArrayList<FavoriteModel>){
//        favorites.clear()
//        favorites.addAll(favorit)
//        notifyDataSetChanged()
//    }

    override fun getItemCount() = data.size
}