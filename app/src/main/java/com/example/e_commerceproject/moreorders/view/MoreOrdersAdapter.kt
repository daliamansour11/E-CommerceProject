package com.example.e_commerceproject.moreorders.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.profile.model.OrderModel


class MoreOrdersAdapter( var context: Context?, var onMoreOrdersClick: OnOrderClickListener): RecyclerView.Adapter<MoreOrdersAdapter.ViewHolder>() {

    private var data:List<OrderModel> = ArrayList()

    fun setDataList(data:List<OrderModel>){
        this.data=data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.orders_row, parent, false)

        val viewHolder: ViewHolder = ViewHolder(v)

        Log.i("TAG", "=========== onCreateViewHolder ===========")
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.orderPrice.text = "${data[position].total_price} EGP"
        holder.orderId.text = "${data[position].id} "
        var createdAt = data[position].created_at.split("T")[0]
        holder.orderDate.text = createdAt
        holder.itemView.setOnClickListener {
            onMoreOrdersClick.onMoreOrderClicked(data[position])
        }
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var orderPrice: TextView = itemView.findViewById(R.id.order_price)
        var orderDate: TextView = itemView.findViewById(R.id.order_date)
        var orderId: TextView = itemView.findViewById(R.id.order_id)

    }

    override fun getItemCount(): Int {
        return data.size
    }
}
