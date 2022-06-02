package com.example.e_commerceproject.cart.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.home.model.DummyProductModel


class CartAdapter (private  var context: Context): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    // define array
    private var data:List<DummyProductModel> = ArrayList()

    fun setDataList(data:List<DummyProductModel>){
        this.data=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.cart_card_view, parent, false)
        val viewHolder: CartAdapter.ViewHolder = ViewHolder(v)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productImage.setImageResource(data[position].productImage)
        holder.productName.text= data[position].productName
        holder.productPrice.text= data[position].productPrice
        holder.productCount.text= data[position].productCount

        holder.incrementBtn.setOnClickListener(View.OnClickListener {

        })

        holder.decrementBtn.setOnClickListener(View.OnClickListener {

        })
    }


    override fun getItemCount(): Int {
        return data.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productName: TextView = itemView.findViewById(R.id.productName)
        var productImage: ImageView = itemView.findViewById(R.id.productImage)
        var productPrice: TextView = itemView.findViewById(R.id.productPrice)
        var productCount : TextView =itemView.findViewById(R.id.productsCount)
        var incrementBtn : Button =itemView.findViewById(R.id.incrementedBtn)
        var decrementBtn : Button =itemView.findViewById(R.id.decrementedBtn)

    }




}


