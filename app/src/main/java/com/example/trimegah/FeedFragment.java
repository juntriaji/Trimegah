package com.example.trimegah;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trimegah.databinding.FragmentFeedBinding;
import com.example.trimegah.model.TModel;
import com.example.trimegah.util.Common;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    private FeedViewModel mViewModel;
    TestJavaAdapter adapter;
    FragmentFeedBinding binding;
    int maxRow = 10;
    int rowHeight = 0;
    int layoutHeight = 0;
    int updateRow = 0;
    Handler handler = new Handler(Looper.myLooper());

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFeedBinding.inflate(inflater, container, false);
        adapter = new TestJavaAdapter(initialData());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setItemAnimator(null);
        binding.recyclerView.postDelayed(this::calculateRowCount, 100);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FeedViewModel.class);
        // TODO: Use the ViewModel
    }

    List<TModel> initialData()
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
        maxRow = getLayoutHeight() / getRowHeight();
        adapter.setData(initialData());
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