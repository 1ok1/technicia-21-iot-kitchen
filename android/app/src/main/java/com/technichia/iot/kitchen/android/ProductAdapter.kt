package com.technichia.iot.kitchen.android

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private val mList: List<Product>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val productList = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(productList.image)

        // sets the text to the textview from our itemHolder class
        holder.name.text = productList.name
        holder.quantity.text = productList.quantity
        holder.avail_quantity.text = productList.avail_quantity
        if (productList.shouldTakeAction)
            holder.avail_quantity.setTextColor("#FF0000".toColorInt())
        else
            holder.avail_quantity.setTextColor("#808080".toColorInt())
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val name: TextView = itemView.findViewById(R.id.name)
        val quantity: TextView = itemView.findViewById(R.id.quantity)
        val avail_quantity: TextView = itemView.findViewById(R.id.avail_quantity)
    }
}

data class Product(val image: Int, val name: String, val quantity: String, val avail_quantity: String, val shouldTakeAction: Boolean)