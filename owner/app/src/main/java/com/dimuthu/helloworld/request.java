package com.dimuthu.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class request extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        getSupportActionBar().setTitle("Request");
    }
}