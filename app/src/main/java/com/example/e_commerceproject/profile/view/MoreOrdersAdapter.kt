package com.example.e_commerceproject.profile.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R


class MoreOrdersAdapter( var context: Context?, var onMoreWishesClick: OnOrderClickListener): RecyclerView.Adapter<MoreOrdersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.product_row, parent, false)

        val viewHolder: ViewHolder = ViewHolder(v)

        Log.i("TAG", "=========== onCreateViewHolder ===========")
        return viewHolder     }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            onMoreWishesClick.onMoreOrderClicked()

        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var order_price: TextView
        var orderdate: TextView


        init {
            orderdate = itemView.findViewById(R.id.order_date)
            order_price = itemView.findViewById(R.id.order_price)

//            itemView.setOnClickListener {
//                val position: Int = adapterPosition
//                Toast.makeText(
//                    itemView.context, "you clicked item on $(productimg[position])",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
        }
    }}
