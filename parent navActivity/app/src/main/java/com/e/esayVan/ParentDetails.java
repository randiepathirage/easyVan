package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ParentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setTitle("Details");
    }
}
