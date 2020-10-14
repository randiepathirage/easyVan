package com.example.ezvanadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class addNewParent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_parent);
        getSupportActionBar().setTitle("Add New Parent");
    }
}