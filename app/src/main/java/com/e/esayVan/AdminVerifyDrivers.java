package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AdminVerifyDrivers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verify_drivers);
        getSupportActionBar().setTitle("Verify Drivers");
    }
}