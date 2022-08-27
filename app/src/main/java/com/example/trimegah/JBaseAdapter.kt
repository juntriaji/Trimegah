package com.example.trimegah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class JBaseAdapter : RecyclerView.Adapter<CellHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, parent, false
        )
        return CellHolder(binding)
    }

    override fun onBindViewHolder(holder: CellHolder, position: Int) {
        val obj = getObjForPosition(position)
        holder.bind(obj)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getObjForPosition(position: Int): Any
    protected abstract fun getLayoutIdForPosition(position: Int): Int
}