package com.lock.the.box.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lock.the.box.ui.DetailsActivity
import com.lock.the.box.databinding.ItemRowBinding
import com.lock.the.box.model.Meals
import java.util.*
import kotlin.collections.ArrayList

class SampleAdapter : RecyclerView.Adapter<SampleAdapter.ViewHolder>() {
    var meals:ArrayList<Meals> =ArrayList()
    private lateinit var binding: ItemRowBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.rowData=meals[position]
        holder.setIsRecyclable(false)
        binding.ctlAllItem.setOnClickListener {
            val intent = Intent(binding.ctlAllItem.context, DetailsActivity::class.java)
            intent.putExtra("Data", meals[position])
            binding.ctlAllItem.context.startActivity(intent)
        }
    }
    override fun getItemCount()=meals.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
          }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(meals: List<Meals>) {
        this.meals= meals as ArrayList<Meals>
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun cleareData(){
        meals.clear()
        notifyDataSetChanged()
    }

}