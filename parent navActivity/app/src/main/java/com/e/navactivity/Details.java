package com.e.navactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setTitle("Details");
    }
}
