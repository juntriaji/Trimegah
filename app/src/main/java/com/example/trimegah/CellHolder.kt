package com.example.trimegah

import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR

class CellHolder(itemBinding: ViewDataBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    var binding: ViewDataBinding = itemBinding

    fun bind(obj: Any)
    {
        this.binding.setVariable(BR.obj, obj)
        this.binding.executePendingBindings()
    }

}
