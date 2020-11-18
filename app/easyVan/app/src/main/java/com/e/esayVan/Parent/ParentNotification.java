package com.e.esayVan.Parent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.e.esayVan.R;

public class ParentNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_notification);
        getSupportActionBar().setTitle("Notifications");
    }
}
