package com.example.ezvanadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class schoolVans extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_vans);
        getSupportActionBar().setTitle("School Vans");
    }
}