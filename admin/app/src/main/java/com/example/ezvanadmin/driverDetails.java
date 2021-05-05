package com.example.ezvanadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class driverDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);
        getSupportActionBar().setTitle("Driver Details");
    }
}