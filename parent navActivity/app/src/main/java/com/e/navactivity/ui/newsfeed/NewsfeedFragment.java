package com.e.navactivity.ui.newsfeed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.e.navactivity.Request;
import com.e.navactivity.R;

public class NewsfeedFragment extends Fragment{
    Button btnRequest;
    private NewsFeedViewModel newsFeedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsFeedViewModel =
                ViewModelProviders.of(this).get(NewsFeedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news_feed, container, false);
        //final TextView textView = root.findViewById(R.id.text_newsFeed);
        newsFeedViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });

        btnRequest=root.findViewById(R.id.btnRequest);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Request.class));
            }
        });
        return root;
    }
}
