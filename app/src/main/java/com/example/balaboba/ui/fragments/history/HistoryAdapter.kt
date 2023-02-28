package com.example.balaboba.ui.fragments.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.balaboba.data.local.room.HistoryFragmentTuple
import com.example.balaboba.databinding.ItemOneBalabobaBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(val binding: ItemOneBalabobaBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var history = listOf<HistoryFragmentTuple>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<HistoryFragmentTuple>) {
        history = list
        notifyDataSetChanged()
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