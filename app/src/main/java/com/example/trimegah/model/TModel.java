package com.example.trimegah.model;

import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import com.example.trimegah.R;
import com.example.trimegah.util.Common;

public class TModel {
    private String feedCode;
    private long time;
    private int price;
    private int change;
    private int volume;

    public String getFeedCode() {
        return feedCode;
    }

    public void setFeedCode(String feedCode) {
        this.feedCode = feedCode;
    }

    public String getTime() {
        return new Common().formatDate(time);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPrice() {
        String formatted = new Common().formatThousand(price);
        String symbol = change>0 ? "▲":"▼";
        return symbol+formatted;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getChange() {
        if(change > 0) return "+"+new Common().formatThousand(change);
        return new Common().formatThousand(change);
    }

    public int getChangeInt(){
        return change;
    }



    public int getColor(View view){
        return ResourcesCompat.getColor(view.getResources(), change > 0 ? R.color.Green1: R.color.Red1, null);

    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getVolume() {
        return new Common().formatThousand(volume);
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public TModel(String feedCode, long time, int price, int change, int volume) {
        this.feedCode = feedCode;
        this.time = time;
        this.price = price;
        this.change = change;
        this.volume = volume;
    }
}
