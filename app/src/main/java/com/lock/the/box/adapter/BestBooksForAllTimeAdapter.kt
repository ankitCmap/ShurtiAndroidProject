package com.lock.the.box.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lock.the.box.R
import com.lock.the.box.databinding.BestBooksOffAllTimeAdapterBinding
import com.lock.the.box.model.StoreWiseProductModel
import com.squareup.picasso.Picasso


class BestBooksForAllTimeAdapter(private val productList: List<StoreWiseProductModel>, val context: Context?) :
    RecyclerView.Adapter<BestBooksForAllTimeAdapter.ViewHolder>() {


    private lateinit var adapterBinding: BestBooksOffAllTimeAdapterBinding

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        adapterBinding = BestBooksOffAllTimeAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(adapterBinding,context)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView.text = productList[position].store_name
        // new recyclerview bind
        adapterBinding.rvBestBookAllTimeChild.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val bestSellerOfHealthBookAdapter = BestSellerOfHealthBookAdapter(productList.get(position))
        adapterBinding.rvBestBookAllTimeChild.adapter = bestSellerOfHealthBookAdapter

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: BestBooksOffAllTimeAdapterBinding, context: Context?) : RecyclerView.ViewHolder(ItemView.root) {
        val textView: TextView = itemView.findViewById(R.id.book_name1)
    }
}