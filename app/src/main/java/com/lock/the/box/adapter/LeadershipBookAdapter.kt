package com.lock.the.box.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lock.the.box.R
import com.lock.the.box.databinding.LeadershipBookAdapterBinding
import com.lock.the.box.model.StoreWiseProductModel
import com.squareup.picasso.Picasso

class LeadershipBookAdapter(private val storeWiseProductModels: List<StoreWiseProductModel>, context: Context?) : RecyclerView.Adapter<LeadershipBookAdapter.ViewHolder>() {

    // create new views
    private lateinit var adapterBinding : LeadershipBookAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterBinding = LeadershipBookAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeadershipBookAdapter.ViewHolder(adapterBinding)

    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //val ItemsViewModel = mList[position]
        Picasso.with(holder.imageView.context).load(storeWiseProductModels[2].product_list[0][0].bookchor_image).into(holder.imageView);
        holder.textView.text = storeWiseProductModels[2].product_list[0][0].title

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return storeWiseProductModels.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: LeadershipBookAdapterBinding) : RecyclerView.ViewHolder(ItemView.root) {

        val imageView: ImageView = itemView.findViewById(R.id.image_books)
        val textView: TextView = itemView.findViewById(R.id.text_books_name)
    }
}