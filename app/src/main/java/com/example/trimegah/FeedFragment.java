package com.example.trimegah;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trimegah.databinding.FragmentFeedBinding;
import com.example.trimegah.model.TModel;
import com.example.trimegah.util.Common;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    TestJavaAdapter adapter;
    FragmentFeedBinding binding;
    int rowHeight = 0;
    int layoutHeight = 0;
    int updateRow = 0;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFeedBinding.inflate(inflater, container, false);
        adapter = new TestJavaAdapter(initialData(1));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setItemAnimator(null);
        binding.recyclerView.postDelayed(this::calculateRowCount, 1000);

        return binding.getRoot();
    }


    List<TModel> initialData(int maxRow)
    {
        List<TModel> t = new ArrayList<>();
        for(int i=0; i <=maxRow; i++ )
        {
            t.add(new Common().generateRandomT());
        }
        return t;
    }

    private int getLayoutHeight() {
        RecyclerView recyclerView = binding.recyclerView;
        if (layoutHeight == 0)
            layoutHeight = recyclerView.getMeasuredHeight();

        return layoutHeight;
    }

    private int getRowHeight() {
        if (rowHeight == 0) {
            View mView = binding.recyclerView.getChildAt(0);
            mView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            rowHeight = mView.getMeasuredHeight();
        }

        return rowHeight;
    }

    private void calculateRowCount() {
        int maxRow = getLayoutHeight() / getRowHeight();
        adapter.setData(initialData(maxRow));
        FeedViewModel feedViewModel = new ViewModelProvider(requireActivity()).get(FeedViewModel.class);
        feedViewModel.getTModel().observe(getViewLifecycleOwner(), tModel -> {
            //Log.e("=>\n", tModel.toString());
            if(updateRow > maxRow) updateRow = 0;
            adapter.getData().set(updateRow, tModel);
            adapter.notifyItemChanged(updateRow);
            updateRow++;

        });
    }

}