package com.example.e_commerceproject.brands.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.home.model.DummyBrandModel
import com.example.e_commerceproject.profile.view.OnOrderClickListener


class BrandsAdapter (var context: Context , val onbrandclicked: OnBrandClicked) : RecyclerView.Adapter<BrandsAdapter.ViewHolder>() {

    var dataList = emptyList<BrandsModel>()

    internal fun setDataList(dataList: MutableList<DummyBrandModel>) {

    }

    // Provide a direct reference to each of the views with data items

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var favourite_img: ImageView
        var product_img: ImageView
        var product_price: TextView
        init {
            favourite_img = itemView.findViewById(R.id.favourite_img)
            product_img = itemView.findViewById(R.id.product_img)
            product_price = itemView.findViewById(R.id.product_price)
        }
    }
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsAdapter.ViewHolder {
        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.categorycustomrow, parent, false)
        return ViewHolder(view)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: BrandsAdapter.ViewHolder, position: Int) {

        // Get the data model based on position
        var data = dataList[position]

        // Set item views based on your views and data model
        holder.product_price.text = data.brandprice
        holder.itemView.setOnClickListener {
            onbrandclicked.onbrandClicked()

        }
        // holder.image.setImageResource(`drawable-sw600dp-hdpi`)

        //  holder.image.setImageResource(data.image)
    }

//    fun setList(favorit: ArrayList<FavoriteModel>){
//        favorites.clear()
//        favorites.addAll(favorit)
//        notifyDataSetChanged()
//    }

    //  total count of items in the list
    override fun getItemCount() = dataList.size
}