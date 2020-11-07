package com.example.appbitsware.ui.descargas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el fragmento de descargas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}