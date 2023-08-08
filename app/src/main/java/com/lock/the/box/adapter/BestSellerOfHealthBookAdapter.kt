package com.lock.the.box.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lock.the.box.R
import com.lock.the.box.databinding.BestSellerOfHealthBookAdapterBinding
import com.lock.the.box.model.StoreWiseProductModel
import com.squareup.picasso.Picasso

class BestSellerOfHealthBookAdapter(private val storeWiseProductModels: StoreWiseProductModel) : RecyclerView.Adapter<BestSellerOfHealthBookAdapter.ViewHolder>() {

    // create new views
    private lateinit var adapterBinding : BestSellerOfHealthBookAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterBinding = BestSellerOfHealthBookAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(adapterBinding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val ItemsViewModel = mList[position]
        Picasso.with(holder.imageView.context).load(storeWiseProductModels.product_list[position][0].bookchor_image).into(holder.imageView);
        holder.textView.text = storeWiseProductModels.product_list[position][0].title

        //val ItemsViewModel = mList[position]


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return storeWiseProductModels.product_list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: BestSellerOfHealthBookAdapterBinding) : RecyclerView.ViewHolder(ItemView.root) {

        val textView: TextView = itemView.findViewById(R.id.text_books_name2)
        val imageView: ImageView = itemView.findViewById(R.id.image_books)
    }
}