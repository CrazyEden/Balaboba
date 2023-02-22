package com.example.balaboba.fragments.stylesinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.balaboba.data.model.Style
import com.example.balaboba.databinding.ItemStyleBinding

class StyleInfoAdapter(
    private var list: List<Style>
) : RecyclerView.Adapter<StyleInfoAdapter.StyleInfoViewHolder>() {
    class StyleInfoViewHolder(val binding:ItemStyleBinding) :RecyclerView.ViewHolder(binding.root)

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