package com.example.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChatsViewModel extends ViewModel {

    private final MutableLiveData<Integer> itemSelected; //position observer
    private final MutableLiveData<Integer> itemsCount; //number of contacts observer

    public ChatsViewModel() {
        itemSelected = new MutableLiveData<Integer>(-1);
        itemsCount = new MutableLiveData<Integer>(0);
        init();
    }

    public void init() {
    }

    public MutableLiveData<Integer> getItemSelected() {
        return itemSelected;
    }

    public void setPosition(Integer item) {
        itemSelected.setValue(item);
    }

    public LiveData<Integer> getSelected() {
        return itemSelected;
    }

    public MutableLiveData<Integer> getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount.setValue(itemsCount);
    }
}