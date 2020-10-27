package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AdminAddnewOwner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_addnew_owner);
        getSupportActionBar().setTitle("Add New Owner");
    }
}