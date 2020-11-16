package com.e.esayVan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

public class OwnerVansAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_vans_add);
        getSupportActionBar().setTitle("Add Vans");

    }
    private Menu menu;
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.owner_appbar, menu);
        return true;
    }
}