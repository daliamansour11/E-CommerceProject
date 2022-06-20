package com.example.e_commerceproject.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceproject.R
import com.example.e_commerceproject.home.model.BrandModel


class BrandsAdapter(private  var context: Context,var onBrandClickListener: OnBrandClickListener): RecyclerView.Adapter<BrandsAdapter.ViewHolder>() {

    private var data:List<BrandModel> = ArrayList()

    fun setDataList(data:List<BrandModel>){
        this.data=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.brands_card, parent, false)
        val viewHolder: BrandsAdapter.ViewHolder = ViewHolder(v)

        return viewHolder
    }

    override fun onBindViewHolder(holder: BrandsAdapter.ViewHolder, position: Int) {

        var imageSrc = data[position].image.src
        Glide.with(context).load(imageSrc).into(holder.brandLogo)
//        holder.brandName.text= data[position].title
        holder.brandCard.setOnClickListener{
            onBrandClickListener.OnBrandClick("${data[position].title}")
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var brandName: TextView = itemView.findViewById(R.id.brandName)
        var brandLogo: ImageView = itemView.findViewById(R.id.brandlogo)
        var brandCard: ConstraintLayout = itemView.findViewById(R.id.brandsCartConstraint)
    }


}


