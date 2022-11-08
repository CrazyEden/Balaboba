package com.example.balaboba.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.balaboba.data.model.Balabob
import com.example.balaboba.databinding.ItemOneBalabobaBinding

class HistoryAdapter:RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class  HistoryViewHolder(val binding: ItemOneBalabobaBinding):RecyclerView.ViewHolder(binding.root)
    var history = listOf<Balabob>()

    fun setData(list: List<Balabob>){
        history = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOneBalabobaBinding.inflate(inflater,parent,false)
        return  HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        with(holder.binding){
            textInput.text = history[position].query
            textRespone.text = history[position].response
            textStyle.text = history[position].style.toString()

        }
    }

    override fun getItemCount(): Int = history.size
}