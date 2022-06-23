package com.example.e_commerceproject.profile.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceproject.R
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.model.LineItem

class WishesAdapter (var context: Context) : RecyclerView.Adapter<WishesAdapter.ViewHolder>() {

    var data: List<DraftOrder> = ArrayList()
    var lineItems: List<LineItem> = ArrayList()

    fun setlist(dataList: List<DraftOrder>){
        this.data = dataList
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var name: TextView
        var price : TextView

        init {
            image = itemView.findViewById(R.id.profile_favorite_imageView)
            name = itemView.findViewById(R.id.profile_fav_name_textview)
            price = itemView.findViewById(R.id.profile_favorite_price_textview)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishesAdapter.ViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.profile_wishlist_customrow, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WishesAdapter.ViewHolder, position: Int) {

        var data = data[position]

        if(data.line_items?.count() != 0 ){
            holder.name.text = ("${data.line_items?.get(0)?.title}")
            holder.price.text = ("${data.line_items?.get(0)?.price}$")
        }

        if(data.note_attributes?.count() != 0 ){
            Glide.with(context).load(data.note_attributes?.get(0)?.value)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.image)
        }

    }

    override fun getItemCount() = data.size
}