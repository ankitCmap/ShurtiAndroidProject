package com.lock.the.box.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lock.the.box.R
import com.lock.the.box.databinding.BestBooksOffAllTimeAdapterBinding
import com.lock.the.box.model.StoreWiseProductModel
import com.squareup.picasso.Picasso

class BestBooksForAllTimeAdapter(private val productList: List<StoreWiseProductModel>, context: Context?) :
    RecyclerView.Adapter<BestBooksForAllTimeAdapter.ViewHolder>() {


    private lateinit var adapterBinding: BestBooksOffAllTimeAdapterBinding

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterBinding = BestBooksOffAllTimeAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(adapterBinding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //val ItemsViewModel = mList[position]
        Picasso.with(holder.imageView.context).load(productList[0].product_list[0][0].bookchor_image).into(holder.imageView);
        holder.textView.text = productList[0].product_list[0][0].title

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: BestBooksOffAllTimeAdapterBinding) : RecyclerView.ViewHolder(ItemView.root) {

        val imageView: ImageView = itemView.findViewById(R.id.image_books)
        val textView: TextView = itemView.findViewById(R.id.text_books_name)
    }
}