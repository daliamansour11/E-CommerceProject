package com.example.e_commerceproject.cart.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceproject.R
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder
import com.example.e_commerceproject.cart.model.LineItem
import com.example.e_commerceproject.cart.model.NoteAttribute

class CartAdapter(private var context: Context, var cartFragment: CartFragment, val onDeleteFromCartListener: OnDeleteFromCartListener) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    // define array
    var cart_id: String = "873008693387"

    var lineItems: List<LineItem> = ArrayList()
    var data1: List<DraftOrder> = ArrayList()
    lateinit var data: CartModel
    private var note_attrubte: List<NoteAttribute> = ArrayList()
    var count: Int = 0
    var dataList = CartModel()

    fun setlist(dataList: List<DraftOrder>) {
        this.data1 = dataList
        //lineItems = data.draft_order.line_items!!
        notifyDataSetChanged()
    }
//    lateinit var data : CartModel
//    internal fun setDataList(dataList1: CartModel) {
//        this.data = dataList1
//       // lineItems = dataList1.draft_order.line_items!!
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.cart_card_view, parent, false)
        val viewHolder: CartAdapter.ViewHolder = ViewHolder(v)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("TESTTEST", "onBindViewHolder: " + note_attrubte.size)
        if (data1[position].note_attributes?.count() != 0) {
            Glide.with(context).load(data1[position].note_attributes?.get(0)?.value)
                .into(holder.productImage)
        }
        holder.productName.text = data1[position].line_items?.get(0)?.name
        holder.productPrice.text = data1[position].line_items?.get(0)?.price
        holder.productCount.text = data1[position].line_items?.get(0)?.quantity.toString()

        //  data[position].note_attributes?.get(0)?.value?.let { holder.productImage.setImageResource(it)) }
        //    holder.productName.text = data1.draft_orders.line_items?.get(position)!!.name
//        holder.productPrice.text = StringBuilder("").append(data1.draft_order.line_items?.get(position)!!.price )
//        holder.productCount.text = data1.draft_orders.line_items?.get(position)!!.quantity.toString()
//
        var id = data1[position].id.toString()
        holder.incrementBtn.setOnClickListener{


            Log.i("TESTTEST", "INCREMENT: ")
            if (count < 6) {
                count++
                data1[position].line_items?.get(0)!!.quantity = count
                holder.productCount.text = data1[position].line_items?.get(0)?.quantity.toString()

                data = CartModel(
                    data1[position]
                )
                data.draft_order?.line_items?.get(0)!!.quantity = count
                cartFragment.upgateCart(id, data)
            }
        //        onDecrementClickListener.onIncrementClicked(data1[position].line_items?.get(0)!!.quantity.toString())

        }

        holder.decrementBtn.setOnClickListener{
            var id = data1[position].id.toString()
            if (count >= 1) {
                count--
                data1[position].line_items?.get(0)!!.quantity = count
                holder.productCount.text = data1[position].line_items?.get(0)?.quantity.toString()
//                data= CartModel(data1[position])
//                cartFragment.upgateCart(cartid  ,data)
                data = CartModel( data1[position] )
                cartFragment.upgateCart(id, data)
            }
           // onDecrementClickListener.onDecrementClicked(data1[position].line_items?.get(0)!!.quantity.toString())

        }
        holder.deleteBtn.setOnClickListener {
            data1[position].id?.let { it1 -> onDeleteFromCartListener.onDeleteFromFavClicked(it1) }
//            cartFragment.deleteitem(cartid)
        }

    }

    override fun getItemCount(): Int {
        return data1.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productName: TextView = itemView.findViewById(R.id.productName)
        var productImage: ImageView = itemView.findViewById(R.id.productImage)
        var productPrice: TextView = itemView.findViewById(R.id.productPrice)
        var productCount: TextView = itemView.findViewById(R.id.productsCount)
        var incrementBtn: Button = itemView.findViewById(R.id.incrementedBtn)
        var decrementBtn: Button = itemView.findViewById(R.id.decrementedBtn)
        var deleteBtn: Button = itemView.findViewById(R.id.delete)
    }

    fun removeSelected(position: Int) {
//    for( i in 0..data1.size-1){
//        if(data1.get(i).line_items)
//            data1.remove(i)
        data1.remove(position)
        notifyDataSetChanged()
    }
//
//    fun removeItem(position:Int) {
//        data1.remove(position)
//        notifyItemRemoved(position)}
}
private fun <E> List<E>.remove(position: Int) {
}



