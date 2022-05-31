package com.example.e_commerceproject.brands.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.profile.view.OnOrderClickListener


class BrandsAdapter(var context: Context?, var brandClick: OnBrandClicked): RecyclerView.Adapter<BrandsAdapter.ViewHolder>() {
    private var productprice = arrayOf("123","550","456")
    private  var productimg = arrayOf(R.drawable.online,R.drawable.online,    R.drawable.online)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.product_row, parent, false)

        val viewHolder: ViewHolder = ViewHolder(v)

        Log.i("TAG", "=========== onCreateViewHolder ===========")
        return viewHolder     }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.product_price.text = productprice[position]
       // holder.product_img.setImageResource(productimg[position])
        holder.itemView.setOnClickListener {
            brandClick.onbrandClicked( )

        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var favourite_img :ImageView
        var product_img : ImageView
        var product_price : TextView

        init{
            favourite_img= itemView.findViewById(R.id.favourite_img)
            product_img = itemView.findViewById(R.id.product_img)
            product_price = itemView.findViewById(R.id.product_price)

            itemView.setOnClickListener{
                val position :Int = adapterPosition
                Toast.makeText(itemView.context,"you clicked item on $(productimg[position])",Toast.LENGTH_LONG ).show()
            }

        }




    }

    class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
        private var productprice = arrayOf("123", "550", "456")
        private var productimg = arrayOf(R.drawable.online, R.drawable.online, R.drawable.online)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val v = layoutInflater.inflate(R.layout.product_row, parent, false)

            val viewHolder: ViewHolder = ViewHolder(v)

            Log.i("TAG", "=========== onCreateViewHolder ===========")
            return viewHolder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.product_price.text = productprice[position]
            holder.product_img.setImageResource(productimg[position])
        }

        override fun getItemCount(): Int {
            return productimg.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var favourite_img: ImageView
            var product_img: ImageView
            var product_price: TextView

            init {
                favourite_img = itemView.findViewById(R.id.favourite_img)
                product_img = itemView.findViewById(R.id.product_img)
                product_price = itemView.findViewById(R.id.product_price)

                itemView.setOnClickListener {
                    val position: Int = adapterPosition
                    Toast.makeText(
                        itemView.context,
                        "you clicked item on $(productimg[position])",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }


        }
    }}