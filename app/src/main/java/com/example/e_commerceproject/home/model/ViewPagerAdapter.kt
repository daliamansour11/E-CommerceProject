package com.example.e_commerceproject.home.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.e_commerceproject.R
import java.util.*

class ViewPagerAdapter(val context: Context, val imageList: List<Int>) : PagerAdapter() {

    lateinit var  layoutInflater : LayoutInflater

    override fun getCount(): Int {
        return imageList.size
    }

    // on below line we are returning the object
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    // on below line we are initializing
    // our item and inflating our layout file
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // on below line we are initializing
        // our layout inflater.
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // on below line we are inflating our custom
        // layout file which we have created.
        val itemView = layoutInflater.inflate(R.layout.slider_item, container, false)

        // on below line we are initializing
        // our image view with the id.
        // on below line we are setting
        val imageView = itemView.findViewById<View>(R.id.image_view) as ImageView
        // image resource for image view.
        imageView.setImageResource(imageList.get(position))

        // on the below line we are adding this
        // item view to the container.
        Objects.requireNonNull(container).addView(itemView)

       /* itemView.setOnClickListener{
            Toast.makeText(context,"images"+(position+1), Toast.LENGTH_LONG).show()
        }*/
        // on below line we are simply
        // returning our item view.
        return itemView
    }

    // on below line we are creating a destroy item method.
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // on below line we are removing view
        container.removeView(`object` as ConstraintLayout)
    }
}