package com.example.balaboba.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.balaboba.data.model.Style
import com.example.balaboba.databinding.ItemStyleBinding

class StyleInfoAdapter : RecyclerView.Adapter<StyleInfoAdapter.StyleInfoViewHolder>() {
    class StyleInfoViewHolder(val binding:ItemStyleBinding) :RecyclerView.ViewHolder(binding.root)

    private var list = listOf<Style>()

    fun setData(list:List<Style>){
        this.list=list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StyleInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStyleBinding.inflate(inflater,parent,false)
        return StyleInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StyleInfoViewHolder, position: Int) {
        holder.binding.styleInfo.text = list[position].styleInfo
        holder.binding.styleLable.text = list[position].styleName
    }

    override fun getItemCount(): Int =list.size

}