package com.example.trimegah;

import androidx.annotation.NonNull;

import com.example.trimegah.model.TModel;

import java.util.ArrayList;
import java.util.List;

public class TestJavaAdapter extends JBaseAdapter{
    public List<TModel> getData() {
        return data;
    }

    private List<TModel> data = new ArrayList<>();

    public TestJavaAdapter(List<TModel> data)
    {
        this.data = data;
    }

    @Override
    public void onBindViewHolder(@NonNull CellHolder holder, int position) {
        TModel model = (TModel) getObjForPosition(position);
        holder.bind(model);
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
