package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ParentEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setTitle("Edit");
    }
}
