package com.example.trimegah;

import androidx.annotation.NonNull;

import com.example.trimegah.databinding.CellBinding;
import com.example.trimegah.model.TModel;

import java.util.List;

public class TestJavaAdapter extends JBaseAdapter{
    public List<TModel> getData() {
        return data;
    }

    public void setData(List<TModel> data) {
        this.data = data;
        for(int i=0; i < this.data.size(); i++)
        {
            notifyItemChanged(i);
        }
    }

    private List<TModel> data;

    public TestJavaAdapter(@NonNull List<TModel> data)
    {
        this.data = data;
    }


    @Override
    public void onBindViewHolder(@NonNull CellHolder holder, int position) {
        TModel model = (TModel) getObjForPosition(position);
        holder.bind(model);

        CellBinding binding = (CellBinding) holder.getBinding();
        binding.change.setTextColor(model.getColor(holder.itemView));
        binding.price.setTextColor(model.getColor(holder.itemView));


    }


    @NonNull
    @Override
    protected Object getObjForPosition(int position) {
        return data.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.cell;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
