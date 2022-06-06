package com.example.e_commerceproject.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceproject.home.model.ProductModel
import com.example.e_commerceproject.R


class HomeSearchAdapter (private  var context: Context): RecyclerView.Adapter<HomeSearchAdapter.ViewHolder>() {

    private var data:List<ProductModel> = ArrayList()

    fun setDataList(data:List<ProductModel>){
        this.data=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.home_searchproducts, parent, false)
        val viewHolder: ViewHolder = ViewHolder(v)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var imageSrc = data[position].image.src
        Glide.with(context).load(imageSrc).into(holder.productImage)
        holder.productName.text= data[position].title
        // may throw null if variants equal null
        holder.productPrice.text= data[position].variants[0].price
    }




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productName: TextView = itemView.findViewById(R.id.homeProductName)
        var productPrice: TextView = itemView.findViewById(R.id.homeProductPrice)
        var productImage: ImageView = itemView.findViewById(R.id.homeProductimageView)

    }

    override fun getItemCount(): Int {
        return data.size
    }


}


