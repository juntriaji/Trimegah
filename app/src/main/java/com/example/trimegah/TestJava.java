package com.example.trimegah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.trimegah.databinding.ActivityTestJavaBinding;
import com.example.trimegah.model.TModel;
import com.example.trimegah.util.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestJava extends AppCompatActivity {
    int maxRow = 50;
    ActivityTestJavaBinding binding;
    TestJavaAdapter adapter;
    Handler handler = new Handler(Looper.myLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestJavaBinding.inflate(getLayoutInflater());

        adapter = new TestJavaAdapter(initialData());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setItemAnimator(null);
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                if(row[0] > adapter.getData().size() -2) row[0] = -1;

                handler.post(() -> {
                    Log.i("->", "" + row[0]);
                    adapter.getData().set(row[0], new Common().generateRandomT());
                    adapter.notifyItemChanged(row[0]);
                });
                row[0]++;
            }
        }, 0,100);
    }
}