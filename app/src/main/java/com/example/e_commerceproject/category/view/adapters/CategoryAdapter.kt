package com.example.e_commerceproject.category.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceproject.R
import com.example.e_commerceproject.category.model.CategoriesModel
import com.example.e_commerceproject.category.view.OnProductClickInterface

class CategoryAdapter (var context: Context , val onProductClickInterface: OnProductClickInterface) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var dataList = emptyList<CategoriesModel>()

    internal fun setDataList(dataList: List<CategoriesModel>) {
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

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.categorycustomrow, parent, false)
        return ViewHolder(view)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {

        // Get the data model based on position
        var data = dataList[position]

        // Set item views based on your views and data model
        holder.title.text = data.title
        holder.productitemview.setOnClickListener {
            onProductClickInterface.onProductClick(data)

        }
        // holder.image.setImageResource(`drawable-sw600dp-hdpi`)

        //  holder.image.setImageResource(data.image)
    }

//    fun setList(favorit: ArrayList<FavoriteModel>){
//        favorites.clear()
//        favorites.addAll(favorit)
//        notifyDataSetChanged()
//    }

    //  total count of items in the list
    override fun getItemCount() = dataList.size
}