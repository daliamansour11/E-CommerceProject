package com.example.e_commerceproject.orderdetails.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.profile.model.LineItemModel

class OrderDetailsAdapter (private  var context: Context): RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder>(){


    private var data:List<LineItemModel> = ArrayList()

    fun setDataList(data:List<LineItemModel>){
        this.data=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.orderdetails_cardview, parent, false)
        val viewHolder: ViewHolder = ViewHolder(v)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text = data[position].title
        holder.orderPrice.text = data[position].price
        holder.orderQuantity.text = data[position].quantity.toString()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var orderPrice: TextView = itemView.findViewById(R.id.orderPriceOrderDetailsCardId)
        var orderQuantity: TextView = itemView.findViewById(R.id.quantityOfOrderDetailsCardId)
        var productName : TextView = itemView.findViewById(R.id.productNametxtViewOrderDetailsCardId)


    }

    override fun getItemCount(): Int {
        return data.size
    }


}