package com.example.e_commerceproject.details.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceproject.R
import com.example.e_commerceproject.category.model.CategoryModel
import com.example.e_commerceproject.category.model.Image

class ImagesAdapter(val context: Context) : RecyclerView.Adapter<ImagesAdapter.MyViewModel>() {

    private var list : List<Image> = ArrayList()
    //var list : String = ""


    fun setListd(listh : List<Image>){
        this.list = listh
        notifyDataSetChanged()

    }
    inner class MyViewModel(view: View) : RecyclerView.ViewHolder(view){

        var image = view.findViewById<ImageView>(R.id.list_images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_of_images, parent, false)
        return MyViewModel(view)
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
        Glide.with(context).load(list[position].src)
            .placeholder(R.drawable.ic_baseline)
            .error(R.drawable.ic_baseline)
            .into(holder.image)
    }

    override fun getItemCount(): Int {

        return list.size
    }
}