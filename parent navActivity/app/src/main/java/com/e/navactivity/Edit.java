package com.e.navactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setTitle("Edit");
    }
}
