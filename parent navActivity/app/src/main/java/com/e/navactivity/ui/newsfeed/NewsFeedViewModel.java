package com.e.navactivity.ui.newsfeed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsFeedViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public NewsFeedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is newsfeed fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}







