package com.example.e_commerceproject.cart.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceproject.R
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.model.LineItem
import com.example.e_commerceproject.cart.model.NoteAttribute


class CartAdapter(private var context: Context, var cartFragment: CartFragment) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    // define array

    var lineItems: List<LineItem> = ArrayList()

    private var note_attrubte: List<NoteAttribute> = ArrayList()
    var count: Int = 0


    lateinit var dataList: DraftOrder

    lateinit private var data: DraftOrder

    fun setlist(dataList: DraftOrder) {
        this.data = dataList
        lineItems = data.line_items!!
        notifyDataSetChanged()
    }

    internal fun setDataList(dataList: DraftOrder) {
        this.dataList = dataList
        lineItems = dataList.line_items!!
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.cart_card_view, parent, false)
        val viewHolder: CartAdapter.ViewHolder = ViewHolder(v)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("TESTTEST", "onBindViewHolder: " + note_attrubte.size)
        //  data[position].note_attributes?.get(0)?.value?.let { holder.productImage.setImageResource(it)) }
        Glide.with(context).load(data.note_attributes?.get(position)?.value)
            .into(holder.productImage)
        holder.productName.text = data.line_items?.get(0)!!.name
        holder.productPrice.text = data.line_items?.get(0)!!.price
        holder.productCount.text = data.line_items?.get(0)!!.quantity.toString()
        holder.incrementBtn.setOnClickListener(View.OnClickListener
        {
            Log.i("TESTTEST", "INCREMENT: " + data.line_items?.size)
            if (count <6) {
                count++
//                var v: Int? = data.line_items?.get(position)!!.quantity
//                var t : Int? = v?.plus(count)
                data.line_items?.get(position)!!.quantity = count
                holder.productCount.text = data.line_items?.get(0)!!.quantity.toString()
                cartFragment.upgateCart(data)
            }

        })

        holder.decrementBtn.setOnClickListener(View.OnClickListener {
            if (count>=1) {
                count--
                data.line_items?.get(position)!!.quantity = count
                holder.productCount.text = data.line_items?.get(0)!!.quantity.toString()
                cartFragment.upgateCart(data)
            }
        })
    }

    override fun getItemCount(): Int {
        return lineItems.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productName: TextView = itemView.findViewById(R.id.productName)
        var productImage: ImageView = itemView.findViewById(R.id.productImage)
        var productPrice: TextView = itemView.findViewById(R.id.productPrice)
        var productCount: TextView = itemView.findViewById(R.id.productsCount)
        var incrementBtn: Button = itemView.findViewById(R.id.incrementedBtn)
        var decrementBtn: Button = itemView.findViewById(R.id.decrementedBtn)


    }


}


