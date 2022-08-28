package com.example.trimegah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestJava extends AppCompatActivity {

    ActivityTestJavaBinding binding;


    ///private ViewPagerAdapter  = new ViewPagerAdapter(getSupportFragmentManager());
    List<TModel> tModelList = new ArrayList<>();
    FeedViewModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataModel = new ViewModelProvider(this).get(FeedViewModel.class);
        binding = ActivityTestJavaBinding.inflate(getLayoutInflater());
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
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FeedFragment());
        fragmentList.add(new BlankFragment());
        fragmentList.add(new BlankFragment());
        fragmentList.add(new BlankFragment());
        fragmentList.add(new BlankFragment());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragmentList);
        binding.viewPager.setAdapter(adapter);
        //calculateRowCount();
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (TabLayoutMediator.TabConfigurationStrategy) (tab, position) -> {
                    try {
                        tab.setText(getResources().getStringArray(R.array.tabmenu)[position]);
                    }catch (Exception ignored){}
                }).attach();

        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //calculateRowCount();
        scheduleTask();
    }

    Handler handler = new Handler(Looper.myLooper());

    void scheduleTask()
    {

        final int[] row = {0};
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                for(int i=0; i < 100; i++){
                    Log.e("test ", ""+i);
                    handler.post(() -> {
                        dataModel.setTModel(new Common().generateRandomT());

                    });
                }

                //Log.e("-> ", "" + maxRow);
            }
        }, 1000,200);
    }


}