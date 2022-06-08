package com.example.e_commerceproject.category.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceproject.R
import com.example.e_commerceproject.category.model.CategoryModel
import com.example.e_commerceproject.category.model.Product
import com.example.e_commerceproject.category.view.OnProductClickInterface

class CategoryAdapter (var context: Context , val onProductClickInterface: OnProductClickInterface) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var dataList : List<CategoryModel> = ArrayList()

    private var data:List<Product> = ArrayList()

    fun setlist(dataList: List<Product>){
        this.data = dataList
    }
    internal fun setDataList(dataList: List<CategoryModel>) {
        this.dataList = dataList
    }

    // Provide a direct reference to each of the views with data items

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var categoryfavorieimageButton : Button
        var productitemview : ConstraintLayout


        init {
            image = itemView.findViewById(R.id.categoriesimageView)
            title = itemView.findViewById(R.id.categoriestextView)
            categoryfavorieimageButton = itemView.findViewById(R.id.categoryfavorieimageButton)
            productitemview = itemView.findViewById(R.id.productitemview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.categorycustomrow, parent, false)
        return ViewHolder(view)
    }

     override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {

        var data = data[position]

         holder.title.text = ("${data.variants[0].price} $")
         Glide.with(context).load(data.image.src)
             .placeholder(R.drawable.ic_launcher_background)
             .error(R.drawable.ic_launcher_foreground)
             .into(holder.image)

         holder.productitemview.setOnClickListener {
            onProductClickInterface.onProductClick()

        }

    }

//    fun setList(favorit: ArrayList<FavoriteModel>){
//        favorites.clear()
//        favorites.addAll(favorit)
//        notifyDataSetChanged()
//    }

    override fun getItemCount() = data.size
}