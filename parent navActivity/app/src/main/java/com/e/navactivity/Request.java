package com.e.navactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Request extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);
        getSupportActionBar().setTitle("Request");
    }
}
