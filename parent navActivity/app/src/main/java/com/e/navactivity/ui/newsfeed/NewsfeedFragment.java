package com.e.navactivity.ui.newsfeed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.e.navactivity.R;

public class NewsfeedFragment extends Fragment{
    private NewsFeedViewModel newsFeedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsFeedViewModel =
                ViewModelProviders.of(this).get(NewsFeedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news_feed, container, false);
        final TextView textView = root.findViewById(R.id.text_newsFeed);
        newsFeedViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
