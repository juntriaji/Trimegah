package com.example.trimegah;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trimegah.model.TModel;

import java.util.List;

public class FeedViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<TModel> tModel = new MutableLiveData<>();
    public void setTModel(TModel tmodel)
    {
        this.tModel.setValue(tmodel);
    }

    public LiveData<TModel> getTModel(){ return this.tModel;}

}