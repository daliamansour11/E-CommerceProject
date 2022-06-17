package com.example.e_commerceproject.home.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.payment.model.DiscountCode


    class CouponsAdapter (private  var context: Context, var onDicountcodeClickListener: OnBrandClickListener): RecyclerView.Adapter<CouponsAdapter.ViewHolder>() {
        private var data: List<DiscountCode> = ArrayList()

        fun setDataList(data: List<DiscountCode>) {
            this.data = data
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): CouponsAdapter.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val v = layoutInflater.inflate(R.layout.coupons_item, parent, false)
            val viewHolder: CouponsAdapter.ViewHolder = ViewHolder(v)
            return viewHolder
        }

        override fun onBindViewHolder(holder: CouponsAdapter.ViewHolder, position: Int) {
            holder.discoubt_code.text = data[position].code
            Log.i("TAG", "onBindViewHolder: couponnnnnnnnnnnnnn")
            holder.discountCard.setOnClickListener {
                data[position].code?.let { it1 -> onDicountcodeClickListener.onDiscountCartClick(it1) }
            }
        }

        override fun getItemCount(): Int {
            return data.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var discoubt_code: TextView = itemView.findViewById(R.id.dicountcode)
            var discountCard: ConstraintLayout = itemView.findViewById(R.id.diccountCodeConstraint)


        }
    }

