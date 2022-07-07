package com.example.lab8;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class SVM extends AndroidViewModel {
    private final MutableLiveData<ArrayList<Country>> countries; //countries observer
    private final MutableLiveData<Integer> itemSelected; //position observer

    public Context context;

    public SVM(@NonNull Application application){
        super(application);
        countries = new MutableLiveData<ArrayList<Country>>();
        itemSelected = new MutableLiveData<Integer>(-1);
        this.context = application.getApplicationContext();
        init(context);
    }

    private void init(Context context) {
        country(CountryXMLParser.parseCountries(context)); // Initialize contacts
    }

    public void country(ArrayList<Country> item) {
        countries.setValue(item);
    }

    public LiveData<ArrayList<Country>> getCountry() {
        return countries;
    }

    public void select(Integer item) {
        itemSelected.setValue(item);
    }

    public LiveData<Integer> getSelected() {
        return itemSelected;
    }
}

