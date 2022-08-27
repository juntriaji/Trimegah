package com.example.trimegah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.example.trimegah.databinding.ActivityTestJavaBinding;
import com.example.trimegah.model.TModel;
import com.example.trimegah.util.Common;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestJava extends AppCompatActivity {
    int maxRow = 10;
    int rowHeight = 0;
    int layoutHeight = 0;
    ActivityTestJavaBinding binding;
    TestJavaAdapter adapter;
    Handler handler = new Handler(Looper.myLooper());
    ///private ViewPagerAdapter  = new ViewPagerAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestJavaBinding.inflate(getLayoutInflater());

        adapter = new TestJavaAdapter(initialData());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setItemAnimator(null);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //calculateRowCount();
        binding.recyclerView.postDelayed(this::calculateRowCount, 100);
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //calculateRowCount();
        scheduleTask();
    }

    List<TModel> initialData()
    {
        List<TModel> t = new ArrayList<>();
        for(int i=0; i <maxRow; i++ )
        {
            t.add(new Common().generateRandomT());
        }
        return t;
    }

    void scheduleTask()
    {

        final int[] row = {0};
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {


                handler.post(() -> {
                    //Log.i("->", "" + row[0]);
                    if(row[0] > adapter.getData().size() -1) row[0] = 0;
                    adapter.getData().set(row[0], new Common().generateRandomT());
                    adapter.notifyItemChanged(row[0]);
                    row[0]++;
                });

                //Log.e("-> ", "" + maxRow);
            }
        }, 1000,100);
    }

    private int getLayoutHeight() {
        RecyclerView recyclerView = binding.recyclerView;
        if (layoutHeight == 0)
            layoutHeight = recyclerView.getMeasuredHeight();

        return layoutHeight;
    }

    private int getRowHeight() {
        if (rowHeight == 0) {
            //ada mAdapter = listRunningTrade.getAdapter();
            //RecyclerView.ViewHolder holder = binding.recyclerView.getChildAt(0);
            View mView = binding.recyclerView.getChildAt(0);
            mView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            rowHeight = mView.getMeasuredHeight();
        }

        return rowHeight;
    }

    private void calculateRowCount() {
        maxRow = getLayoutHeight() / getRowHeight();
        //initialData();
        adapter.setData(initialData());
    }
}