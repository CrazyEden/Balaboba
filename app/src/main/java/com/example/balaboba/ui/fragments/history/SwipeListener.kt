package com.example.balaboba.ui.fragments.history

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeListener(
    private val removeAction:(position:Int) -> Unit
) : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        removeAction(viewHolder.adapterPosition)
    }
    fun attachToRecyclerView(view:RecyclerView){
        ItemTouchHelper(this).attachToRecyclerView(view)
    }
}