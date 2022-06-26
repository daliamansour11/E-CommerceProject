package com.example.e_commerceproject.favorite.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceproject.R
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.model.LineItem

class FavoriteAdapter (var context: Context , val onDeletefromFavoriteClikListener: OnDeletefromFavoriteClikListener) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    var data: List<DraftOrder> = ArrayList()
    var lineItems: List<LineItem> = ArrayList()

    fun setlist(dataList: List<DraftOrder>){
        this.data = dataList
    }
    fun setDatalist(dataList: List<LineItem>){
        this.lineItems = dataList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var name: TextView
        var price : TextView
        var deletefromFav : Button
        var addtoCart : Button


        init {
            image = itemView.findViewById(R.id.favorite_imageView)
            name = itemView.findViewById(R.id.fav_name_textview)
            price = itemView.findViewById(R.id.favorite_price_textview)
            deletefromFav = itemView.findViewById(R.id.favorite_delete_from_favorieButton)
            addtoCart = itemView.findViewById(R.id.favorite_addtocartbtn)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.favoriecustomrow, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {

        var data = data[position]


        if(data.line_items?.count() != 0 ){
            holder.name.text = ("${data.line_items?.get(0)?.title}")
            holder.price.text = ("${data.line_items?.get(0)?.price} EGP")

        }

        if(data.note_attributes?.count() != 0 ){
            Glide.with(context).load(data.note_attributes?.get(0)?.value)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.image)
        }
        holder.deletefromFav.setOnClickListener {
            onDeletefromFavoriteClikListener.onDeleteFromFavClicked("${data.id}")
        }

    }

    override fun getItemCount() = data.size
}