package com.example.appbitsware.ui.cursos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Este es el fragmento de cursos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}