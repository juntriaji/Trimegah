package com.example.trimegah.util

import android.widget.Adapter
import com.example.trimegah.CellHolder
import com.example.trimegah.JBaseAdapter
import com.example.trimegah.model.RunningTradeModel

class MainAdapter(private val layoutId: Int, val data: MutableList<RunningTradeModel>) :
    JBaseAdapter() {


    fun setData(model: RunningTradeModel, position: Int)
    {
        data[position] = model
        notifyItemChanged(position)
    }


    override fun onBindViewHolder(holder: CellHolder, position: Int) {
        val item = getObjForPosition(holder.adapterPosition)
        holder.bind(item)

    }
    override fun getObjForPosition(position: Int): Any {
        return data[position]
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun getItemCount(): Int {
        return data.size
    }

}