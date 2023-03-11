package com.example.balaboba.ui.fragments.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.balaboba.data.local.room.BalabobEntity
import com.example.balaboba.databinding.ItemOneBalabobaBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(val binding: ItemOneBalabobaBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var history = mutableListOf<BalabobEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<BalabobEntity>) {
        history = list.toMutableList()
        notifyDataSetChanged()
    }
    fun getId(position: Int):Long = history[position].id
    fun removeItem(position: Int){
        history.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOneBalabobaBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        with(holder.binding) {
            textInput.text = history[position].query
            textRespone.text = history[position].response
            textStyle.text = history[position].style
        }
    }

    override fun getItemCount(): Int = history.size
}