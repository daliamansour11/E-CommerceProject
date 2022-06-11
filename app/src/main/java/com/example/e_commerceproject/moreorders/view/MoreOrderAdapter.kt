package com.example.e_commerceproject.moreorders.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.homesearch.model.ProductModel


class MoreOrderAdapter(private  var context: Context): RecyclerView.Adapter<MoreOrderAdapter.ViewHolder>(){


    private var data:List<ProductModel> = ArrayList()

    fun setDataList(data:List<ProductModel>){
        this.data=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.orders_row, parent, false)
        val viewHolder: ViewHolder = ViewHolder(v)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var orderPrice: TextView = itemView.findViewById(R.id.order_price)
        var orderDate: TextView = itemView.findViewById(R.id.order_date)


    }

    override fun getItemCount(): Int {
        return data.size
    }


}